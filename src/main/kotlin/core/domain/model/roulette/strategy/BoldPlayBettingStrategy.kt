package org.example.core.domain.model.roulette.strategy

import org.example.core.domain.model.roulette.BettingStrategy
import org.example.core.domain.model.roulette.RouletteRound
import kotlin.math.min

class BoldPlayBettingStrategy : BettingStrategy {

    override fun nextBet(lastRound: RouletteRound?, currentBalance: Int, initialBalance: Int, targetWinnings: Int): Int {
        val targetBalance = initialBalance + targetWinnings
        val amountNeeded = targetBalance - currentBalance

        val bet = min(currentBalance, amountNeeded)

        return bet.coerceAtLeast(1)
    }

    override fun reset() {
        // No state to reset
    }
}
