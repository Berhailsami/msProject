package core.domain.model.iterated_prisoners_dilemma

import core.domain.model.iterated_prisoners_dilemma.strategy.IPDStrategy
import core.domain.model.iterated_prisoners_dilemma.Move

class Player(val strategy: IPDStrategy) {
    var score = 0

    fun play(opponentLastMove: Move?): Move {
        return strategy.play(opponentLastMove)
    }

    fun reset() {
        score = 0
    }
}
