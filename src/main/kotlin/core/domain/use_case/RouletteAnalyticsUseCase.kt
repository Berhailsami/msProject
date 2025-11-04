package org.example.core.domain.use_case

import kotlin.math.pow

class RouletteAnalyticsUseCase {

    /**
     * Calculates the probability of success in a roulette game using the Gambler's Ruin formula.
     * This is only valid for a fixed bet strategy (S1).
     *
     * @param initialBalance The starting balance of the player.
     * @param targetWinnings The amount of winnings the player wants to achieve.
     * @param betAmount The fixed amount to bet in each round.
     * @return The probability of reaching the target winnings before going bankrupt.
     */
    fun calculateWinProbability(
        initialBalance: Int,
        targetWinnings: Int,
        betAmount: Int
    ): Double {

        if (betAmount <= 0 || initialBalance <= 0 || targetWinnings <= 0) return 0.0

        val pWin = 18.0 / 38.0
        val pLose = 1.0 - pWin

        val initialUnits = initialBalance.toDouble() / betAmount.toDouble()
        val targetBalance = initialBalance + targetWinnings
        val targetUnits = targetBalance.toDouble() / betAmount.toDouble()

        val ratio = pLose / pWin

        val ratioPowI = ratio.pow(initialUnits)
        val ratioPowN = ratio.pow(targetUnits)

        if (ratioPowN.isInfinite()) {
            return if (ratio > 1) 0.0 else 1.0
        }
        
        val numerator = 1.0 - ratioPowI
        val denominator = 1.0 - ratioPowN

        if (denominator == 0.0) {
            return 0.0
        }

        return numerator / denominator
    }
}
