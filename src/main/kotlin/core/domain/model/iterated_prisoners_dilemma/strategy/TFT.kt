package core.domain.model.iterated_prisoners_dilemma.strategy

import core.domain.model.iterated_prisoners_dilemma.Move

class TFT : IPDStrategy {
    override val name = "TFT"
    override fun play(opponentLastMove: Move?): Move {
        return opponentLastMove ?: Move.COOPERATE
    }
}
