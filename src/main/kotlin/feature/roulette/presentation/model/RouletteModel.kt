package org.example.feature.roulette.presentation.model

import org.example.core.domain.model.roulette.BettingStrategy
import org.example.core.domain.model.roulette.RouletteGame
import org.example.core.domain.model.roulette.strategy.S1BettingStrategy
import javax.swing.SwingUtilities

class RouletteModel {
    
    private val listeners = mutableListOf<RouletteListener>()
    
    var game = RouletteGame(
        initialBalance = 0,
        targetWinnings = 0,
        currentBalance = 0,
        totalWinnings = 0,
        rounds = emptyList(),
        isComplete = false,
        bettingStrategy = S1BettingStrategy()
    )
        private set
    
    private val history = ArrayDeque<RouletteGame>()
    
    fun addListener(listener: RouletteListener) {
        listeners.add(listener)
    }
    
    fun notifyListeners() {
        SwingUtilities.invokeLater {
            listeners.forEach { listener ->
                listener.onGameUpdated(game)
            }
        }
    }
    
    fun setGame(game: RouletteGame, pushToHistory: Boolean = false) {
        if (pushToHistory) {
            history.addLast(this.game)
        }
        this.game = game
        notifyListeners()
    }
    
    fun initializeGame(initialBalance: Int, targetWinnings: Int) {
        val newGame = RouletteGame(
            initialBalance = initialBalance,
            targetWinnings = targetWinnings,
            currentBalance = initialBalance,
            totalWinnings = 0,
            rounds = emptyList(),
            isComplete = false,
            bettingStrategy = game.bettingStrategy
        )
        history.clear()
        setGame(newGame)
    }
    
    fun reset() {
        history.clear()
        val freshGame = RouletteGame(
            initialBalance = 0,
            targetWinnings = 0,
            currentBalance = 0,
            totalWinnings = 0,
            rounds = emptyList(),
            isComplete = false,
            bettingStrategy = S1BettingStrategy()
        )
        setGame(freshGame)
    }
    
    fun stepBack() {
        if (history.isNotEmpty()) {
            val previous = history.removeLast()
            setGame(previous)
        }
    }

    fun changeStrategy(strategy: BettingStrategy) {
        this.game = this.game.copy(bettingStrategy = strategy)
    }
}
