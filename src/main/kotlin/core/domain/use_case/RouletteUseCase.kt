package org.example.core.domain.use_case

import org.example.core.domain.model.roulette.BallColor
import org.example.core.domain.model.roulette.BetColor
import org.example.core.domain.model.roulette.BettingStrategy
import org.example.core.domain.model.roulette.RouletteGame
import org.example.core.domain.model.roulette.RouletteRound
import java.util.concurrent.ThreadLocalRandom

/**
 * Use case for simulating American Roulette
 */
class RouletteUseCase {

    // Correctly models the numbers and colors on an American roulette wheel.
    private companion object {
        val americanWheelLayout = listOf(
            0 to BallColor.ZERO, 28 to BallColor.BLACK, 9 to BallColor.RED, 26 to BallColor.BLACK,
            30 to BallColor.RED, 11 to BallColor.BLACK, 7 to BallColor.RED, 20 to BallColor.BLACK,
            32 to BallColor.RED, 17 to BallColor.BLACK, 5 to BallColor.RED, 22 to BallColor.BLACK,
            34 to BallColor.RED, 15 to BallColor.BLACK, 3 to BallColor.RED, 24 to BallColor.BLACK,
            36 to BallColor.RED, 13 to BallColor.BLACK, 1 to BallColor.RED, 37 to BallColor.DOUBLE_ZERO, // 37 represents "00"
            27 to BallColor.RED, 10 to BallColor.BLACK, 25 to BallColor.RED, 29 to BallColor.BLACK,
            12 to BallColor.RED, 8 to BallColor.BLACK, 19 to BallColor.RED, 31 to BallColor.BLACK,
            18 to BallColor.RED, 6 to BallColor.BLACK, 21 to BallColor.RED, 33 to BallColor.BLACK,
            16 to BallColor.RED, 4 to BallColor.BLACK, 23 to BallColor.RED, 35 to BallColor.BLACK,
            14 to BallColor.RED, 2 to BallColor.BLACK
        )
    }
    
    /**
     * Simulates one round of roulette
     * Returns a new game state after the round
     */
    fun invoke(
        currentGame: RouletteGame,
        betAmount: Int,
        betColor: BetColor,
        recordRounds: Boolean = true
    ): RouletteGame {
        if (currentGame.isComplete) return currentGame
        
        if (!hasEnoughBalance(currentGame.currentBalance, betAmount)) {
            return currentGame.copy(isComplete = true, success = false)
        }
        
        val ballColor = spinWheel()
        val won = determineIfWon(ballColor, betColor)
        
        val balanceAfter = calculateNewBalance(currentGame.currentBalance, betAmount, won)
        val totalWinnings = balanceAfter - currentGame.initialBalance
        
        val round = createRound(
            roundNumber = currentGame.rounds.size + 1,
            betAmount = betAmount,
            betColor = betColor,
            ballColor = ballColor,
            balanceBefore = currentGame.currentBalance,
            balanceAfter = balanceAfter,
            gameResult = won
        )
        
        val (isComplete, success) = checkGameCompletion(totalWinnings, currentGame.targetWinnings, balanceAfter)
        
        return currentGame.copy(
            currentBalance = balanceAfter,
            totalWinnings = totalWinnings,
            rounds = if (recordRounds) currentGame.rounds + round else emptyList(),
            isComplete = isComplete,
            success = success
        )
    }

    /**
     * Simulates a full game from start to finish without creating intermediate states.
     * This is optimized for performance in auto-simulation scenarios.
     * Returns true for a successful game (target winnings reached), false otherwise.
     */
    fun simulateFullGame(
        initialBalance: Int,
        targetWinnings: Int,
        betColor: BetColor,
        strategy: BettingStrategy
    ): Boolean {
        var currentBalance = initialBalance
        var lastRound: RouletteRound? = null

        while (true) {
            val totalWinnings = currentBalance - initialBalance
            if (totalWinnings >= targetWinnings) {
                return true // Success
            }

            val betAmount = strategy.nextBet(lastRound, currentBalance, initialBalance, targetWinnings)

            if (currentBalance < betAmount) {
                return false // Failure
            }

            val ballColor = spinWheel()
            val won = determineIfWon(ballColor, betColor)

            val previousBalance = currentBalance
            currentBalance = if (won) {
                currentBalance + betAmount
            } else {
                currentBalance - betAmount
            }

            lastRound = RouletteRound(
                roundNumber = (lastRound?.roundNumber ?: 0) + 1,
                betAmount = betAmount,
                betColor = betColor,
                ballColor = ballColor,
                balanceBefore = previousBalance,
                balanceAfter = currentBalance,
                gameResult = won
            )
        }
    }
    
    private fun hasEnoughBalance(balance: Int, betAmount: Int): Boolean {
        return balance >= betAmount
    }
    
    /**
     * Spins the roulette wheel and returns the color the ball lands on.
     * This now correctly models the layout of an American roulette wheel.
     */
    private fun spinWheel(): BallColor {
        val pocketIndex = ThreadLocalRandom.current().nextInt(americanWheelLayout.size)
        return americanWheelLayout[pocketIndex].second
    }
    
    private fun determineIfWon(ballColor: BallColor, betColor: BetColor): Boolean {
        return when {
            ballColor == BallColor.ZERO || ballColor == BallColor.DOUBLE_ZERO -> false
            ballColor == BallColor.RED && betColor == BetColor.RED -> true
            ballColor == BallColor.BLACK && betColor == BetColor.BLACK -> true
            else -> false
        }
    }
    
    private fun calculateNewBalance(currentBalance: Int, betAmount: Int, won: Boolean): Int {
        return if (won) {
            currentBalance + betAmount
        } else {
            currentBalance - betAmount
        }
    }
    
    private fun createRound(
        roundNumber: Int,
        betAmount: Int,
        betColor: BetColor,
        ballColor: BallColor,
        balanceBefore: Int,
        balanceAfter: Int,
        gameResult: Boolean
    ): RouletteRound {
        return RouletteRound(
            roundNumber = roundNumber,
            betAmount = betAmount,
            betColor = betColor,
            ballColor = ballColor,
            balanceBefore = balanceBefore,
            balanceAfter = balanceAfter,
            gameResult = gameResult
        )
    }
    
    private fun checkGameCompletion(totalWinnings: Int, targetWinnings: Int, balanceAfter: Int): Pair<Boolean, Boolean?> {
        val hasWonTarget = totalWinnings >= targetWinnings
        val hasLost = balanceAfter <= 0
        
        val isComplete = hasWonTarget || hasLost
        val success = when {
            hasWonTarget -> true
            hasLost -> false
            else -> null
        }
        
        return isComplete to success
    }
}
