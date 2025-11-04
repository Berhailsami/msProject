package org.example.core.domain.model.roulette

/**
 * Defines a strategy for determining the bet amount in a roulette game.
 */
interface BettingStrategy {
    /**
     * Calculates the next bet amount based on the previous round's outcome and current game state.
     *
     * @param lastRound The previous round, or null if this is the first round.
     * @param currentBalance The player's current balance.
     * @param initialBalance The player's initial balance.
     * @param targetWinnings The player's target winnings.
     * @return The bet amount for the next round.
     */
    fun nextBet(lastRound: RouletteRound?, currentBalance: Int, initialBalance: Int, targetWinnings: Int): Int

    /**
     * Resets the strategy to its initial state.
     */
    fun reset()
}
