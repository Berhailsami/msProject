package core.domain.model.iterated_prisoners_dilemma.strategy

import core.domain.model.iterated_prisoners_dilemma.Move

class Punishement : IPDStrategy {
    override val name = "Punishement"

    private var historyLastMove: Move? = null
    private var historySecondLastMove: Move? = null
    private var punishementCounter : Int = 0

    override fun play(opponentLastMove: Move?): Move {
        if (opponentLastMove == null) {
            return Move.DEFECT
        }

        while (punishementCounter > 0) {
            punishementCounter--
            return Move.DEFECT
        }

        this.historySecondLastMove = this.historyLastMove
        this.historyLastMove = opponentLastMove

        return if (historyLastMove == Move.DEFECT && historySecondLastMove == Move.DEFECT) {
            punishementCounter++
            Move.DEFECT
        } else {
            Move.COOPERATE
        }
    }

    fun reset() {
        historyLastMove = null
        historySecondLastMove = null
    }
}