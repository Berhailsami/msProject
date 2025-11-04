package org.example.core.domain.model.roulette.strategy

import org.example.core.domain.model.roulette.BettingStrategy
import org.example.core.domain.model.roulette.RouletteRound

/**
 * A betting strategy where the bet is doubled after a loss and reset to a base amount after a win.
 */
class MartingaleBettingStrategy(private val initialBet: Int = 1) : BettingStrategy {
    private var currentBet = initialBet

    override fun nextBet(lastRound: RouletteRound?, currentBalance: Int, initialBalance: Int, targetWinnings: Int): Int {
        return if (lastRound == null) {
            initialBet
        } else if (lastRound.gameResult) {
            currentBet = initialBet
            initialBet
        } else {
            currentBet *= 2
            currentBet
        }
    }

    override fun reset() {
        currentBet = initialBet
    }
}
