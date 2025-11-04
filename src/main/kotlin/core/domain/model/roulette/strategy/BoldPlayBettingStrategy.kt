package org.example.core.domain.model.roulette.strategy

import org.example.core.domain.model.roulette.BettingStrategy
import org.example.core.domain.model.roulette.RouletteRound
import kotlin.math.min

/**
 * A strategy to maximize the probability of winning by betting aggressively.
 * This is also known as "Bold Play".
 */
class BoldPlayBettingStrategy : BettingStrategy {

    override fun nextBet(lastRound: RouletteRound?, currentBalance: Int, initialBalance: Int, targetWinnings: Int): Int {
        val targetBalance = initialBalance + targetWinnings
        val amountNeeded = targetBalance - currentBalance

        // If we are more than halfway to the goal, bet what is needed to win.
        // Otherwise, bet everything.
        val bet = min(currentBalance, amountNeeded)

        return bet.coerceAtLeast(1)
    }

    override fun reset() {
        // No state to reset
    }
}
