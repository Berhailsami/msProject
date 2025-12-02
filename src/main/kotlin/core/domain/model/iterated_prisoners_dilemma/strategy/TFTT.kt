package core.domain.model.iterated_prisoners_dilemma.strategy

import core.domain.model.iterated_prisoners_dilemma.Move

class TFTT : IPDStrategy {
    override val name = "TFTT"
    private var opponentLastMove: Move? = null
    private var opponentSecondLastMove: Move? = null

    override fun play(opponentLastMove: Move?): Move {
        val decision = if (this.opponentLastMove == Move.DEFECT && opponentLastMove == Move.DEFECT) {
            Move.DEFECT
        } else {
            Move.COOPERATE
        }

        // Update history for the next round
        this.opponentSecondLastMove = this.opponentLastMove
        this.opponentLastMove = opponentLastMove

        return decision
    }

    fun reset() {
        opponentLastMove = null
        opponentSecondLastMove = null
    }
}
