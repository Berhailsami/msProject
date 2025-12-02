package core.domain.use_case

import core.domain.model.iterated_prisoners_dilemma.Player
import core.domain.model.iterated_prisoners_dilemma.Tournament
import core.domain.model.iterated_prisoners_dilemma.TournamentResult
import core.domain.model.iterated_prisoners_dilemma.strategy.Cooperator
import core.domain.model.iterated_prisoners_dilemma.strategy.Defector
import core.domain.model.iterated_prisoners_dilemma.strategy.Random
import core.domain.model.iterated_prisoners_dilemma.strategy.TFT
import core.domain.model.iterated_prisoners_dilemma.strategy.TFTT

class IteratedPrisonersDilemmaUseCase {

    fun invoke(
        numCooperators: Int,
        numDefectors: Int,
        numRandoms: Int,
        numTFTs: Int,
        numTFTTs: Int = 0
    ): TournamentResult {
        val players = mutableListOf<Player>()
        repeat(numCooperators) { players.add(Player(Cooperator())) }
        repeat(numDefectors) { players.add(Player(Defector())) }
        repeat(numRandoms) { players.add(Player(Random())) }
        repeat(numTFTs) { players.add(Player(TFT())) }
        repeat(numTFTTs) { players.add(Player(TFTT())) }

        val tournament = Tournament(players)
        return tournament.run()
    }
}
