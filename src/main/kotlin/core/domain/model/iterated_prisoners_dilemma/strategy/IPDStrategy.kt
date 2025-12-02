package core.domain.model.iterated_prisoners_dilemma.strategy

import core.domain.model.iterated_prisoners_dilemma.Move

interface IPDStrategy {
    val name: String
    fun play(opponentLastMove: Move?): Move
}
