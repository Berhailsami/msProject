package core.domain.model.iterated_prisoners_dilemma.strategy

import core.domain.model.iterated_prisoners_dilemma.Move

class Defector : IPDStrategy {
    override val name = "Defector"
    override fun play(opponentLastMove: Move?) = Move.DEFECT
}
