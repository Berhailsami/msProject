package core.domain.model.iterated_prisoners_dilemma.strategy

import core.domain.model.iterated_prisoners_dilemma.Move
import java.util.concurrent.ThreadLocalRandom

class Random : IPDStrategy {
    override val name = "Random"
    override fun play(opponentLastMove: Move?): Move {
        return if (ThreadLocalRandom.current().nextBoolean()) Move.COOPERATE else Move.DEFECT
    }
}
