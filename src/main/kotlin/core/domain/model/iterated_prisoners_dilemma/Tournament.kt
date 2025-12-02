package core.domain.model.iterated_prisoners_dilemma

import core.domain.model.iterated_prisoners_dilemma.Move


class Tournament(
    val players: List<Player>,
    private val roundsPerContest: Int = 10
) {
    private val payoffMatrix = mapOf(
        (Move.COOPERATE to Move.COOPERATE) to (4 to 4),
        (Move.COOPERATE to Move.DEFECT) to (0 to 5),
        (Move.DEFECT to Move.COOPERATE) to (5 to 0),
        (Move.DEFECT to Move.DEFECT) to (2 to 2)
    )

    fun run(): TournamentResult {
        players.forEach { player->
            player.reset()
        }

        for (i in players.indices) {
            for (j in i + 1 until players.size) {
                playContest(players[i], players[j])
            }
        }

        return calculateResults()
    }

    private fun playContest(player1: Player, player2: Player) {
        var p1LastMove: Move? = null
        var p2LastMove: Move? = null

        for (round in 1..roundsPerContest) {
            val p1Move = player1.play(p2LastMove)
            val p2Move = player2.play(p1LastMove)

            val (p1Score, p2Score) = payoffMatrix[p1Move to p2Move]!!
            player1.score += p1Score
            player2.score += p2Score

            p1LastMove = p1Move
            p2LastMove = p2Move
        }
    }

    private fun calculateResults(): TournamentResult {
        val scoresByStrategy = mutableMapOf<String, MutableList<Int>>()
        players.forEach { player ->
            scoresByStrategy.getOrPut(player.strategy.name) { mutableListOf() }.add(player.score)
        }

        val averageScores = scoresByStrategy.mapValues { (_, scores) ->
            scores.average()
        }

        return TournamentResult(averageScores)
    }
}
