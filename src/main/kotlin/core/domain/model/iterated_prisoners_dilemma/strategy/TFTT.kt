package core.domain.model.iterated_prisoners_dilemma.strategy

import core.domain.model.iterated_prisoners_dilemma.Move

class TFTT : IPDStrategy {
    override val name = "TFTT"

    private var historyLastMove: Move? = null
    private var historySecondLastMove: Move? = null

    override fun play(opponentLastMove: Move?): Move {
        if (opponentLastMove == null) {
            return Move.COOPERATE
        }

        this.historySecondLastMove = this.historyLastMove
        this.historyLastMove = opponentLastMove

        return if (historyLastMove == Move.DEFECT && historySecondLastMove == Move.DEFECT) {
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