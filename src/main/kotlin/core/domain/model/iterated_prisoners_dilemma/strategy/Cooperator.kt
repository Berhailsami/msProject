package core.domain.model.iterated_prisoners_dilemma.strategy

import core.domain.model.iterated_prisoners_dilemma.Move

class Cooperator : IPDStrategy {
    override val name = "Cooperator"
    override fun play(opponentLastMove: Move?) = Move.COOPERATE
}
