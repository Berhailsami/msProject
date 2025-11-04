package org.example.core.domain.model.roulette.strategy

import org.example.core.domain.model.roulette.BettingStrategy
import org.example.core.domain.model.roulette.RouletteRound

/**
 * A simple betting strategy where the bet amount is always a fixed value.
 */
class S1BettingStrategy(private val betAmount: Int = 1) : BettingStrategy {
    override fun nextBet(lastRound: RouletteRound?, currentBalance: Int, initialBalance: Int, targetWinnings: Int): Int {
        return betAmount
    }

    override fun reset() {
        // No state to reset
    }
}
