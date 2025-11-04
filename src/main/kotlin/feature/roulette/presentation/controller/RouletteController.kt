package org.example.feature.roulette.presentation.controller

import org.example.core.domain.model.roulette.BetColor
import org.example.core.domain.model.roulette.strategy.S1BettingStrategy
import org.example.core.domain.use_case.RouletteAnalyticsUseCase
import org.example.core.domain.use_case.RouletteUseCase
import org.example.feature.roulette.presentation.model.RouletteModel
import org.example.feature.roulette.presentation.view.RouletteControls
import org.example.feature.roulette.presentation.view.RouletteView
import kotlinx.coroutines.*
import org.example.core.domain.model.roulette.strategy.BettingStrategy
import java.util.concurrent.atomic.LongAdder
import javax.swing.SwingUtilities

class RouletteController(
    private val model: RouletteModel,
    private val view: RouletteView,
    private val rouletteUseCase: RouletteUseCase = RouletteUseCase(),
    private val analyticsUseCase: RouletteAnalyticsUseCase = RouletteAnalyticsUseCase()
) : RouletteControls {
    
    private var initialBalance: Int = 0
    private var targetWinnings: Int = 0
    private var betColor: BetColor = BetColor.RED
    private var bettingStrategy: BettingStrategy = S1BettingStrategy()
    private val controllerScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    
    init {
        view.setControls(this)
        model.addListener(view.gameView)
        
        // Trigger initial update
        model.notifyListeners()
    }
    
    override fun onInitialBalanceChanged(balance: Int) {
        SwingUtilities.invokeLater {
            initialBalance = balance
            updateTheoreticalProbability()
        }
    }
    
    override fun onTargetWinningsChanged(target: Int) {
        SwingUtilities.invokeLater {
            targetWinnings = target
            updateTheoreticalProbability()
        }
    }
    
    override fun onBetAmountChanged(amount: Int) {
        // This is handled by onBettingStrategyChanged
    }
    
    override fun onBetColorChanged(color: BetColor) {
        SwingUtilities.invokeLater {
            betColor = color
        }
    }

    override fun onBettingStrategyChanged(strategy: BettingStrategy) {
        SwingUtilities.invokeLater {
            model.changeStrategy(strategy)
            updateTheoreticalProbability()
        }
    }
    
    override fun onInitializeClicked() {
        SwingUtilities.invokeLater {
            model.initializeGame(initialBalance, targetWinnings)
        }
    }
    
    override fun onStepClicked() {
        SwingUtilities.invokeLater {
            if (model.game.isComplete) return@invokeLater
            if (model.game.rounds.isEmpty() && (initialBalance == 0 || targetWinnings == 0)) {
                // Can't start without initialization
                return@invokeLater
            }
            
            val lastRound = model.game.rounds.lastOrNull()
            val nextBet = bettingStrategy.nextBet(lastRound)

            // If game hasn't been initialized, initialize it first
            val game = if (model.game.rounds.isEmpty()) {
                val newGame = model.game.copy(
                    initialBalance = initialBalance,
                    targetWinnings = targetWinnings,
                    currentBalance = initialBalance
                )
                rouletteUseCase.invoke(newGame, nextBet, betColor)
            } else {
                rouletteUseCase.invoke(model.game, nextBet, betColor)
            }
            
            model.setGame(game, pushToHistory = true)
        }
    }
    
    override fun onBackClicked() {
        SwingUtilities.invokeLater {
            model.stepBack()
        }
    }
    
    override fun onResetClicked() {
        SwingUtilities.invokeLater {
            model.reset()

        }
    }
    
    override fun onAutoSimulateClicked() {
        val numSimulations = view.controlsView.numSimulationsField.text.toIntOrNull() ?: 1000
        
        if (initialBalance == 0 || targetWinnings == 0) {
            return
        }
        
        view.controlsView.autoSimulateButton.isEnabled = false
        view.controlsView.autoSimulateButton.text = "Simulating..."
        view.statisticsView.updateStatistics(0, 0)
        
        val successful = LongAdder()
        val failed = LongAdder()

        // Launch the simulation and UI updates using coroutines
        controllerScope.launch {
            // UI update job
            val uiUpdateJob = launch {
                while (isActive) {
                    val currentSuccessful = successful.sum().toInt()
                    val currentFailed = failed.sum().toInt()
                    val progress = currentSuccessful + currentFailed
                    val percentage = if (numSimulations > 0) (progress * 100) / numSimulations else 0

                    view.statisticsView.updateStatistics(currentSuccessful, currentFailed)
                    if (progress < numSimulations) {
                        view.controlsView.autoSimulateButton.text = "Simulating... $percentage%"
                    }
                    delay(100) // Update UI every 100ms
                }
            }

            // Simulation worker job
            val simulationJob = launch(Dispatchers.Default) {
                val numThreads = Runtime.getRuntime().availableProcessors()
                val simulationsPerThread = numSimulations / numThreads
                val remainder = numSimulations % numThreads

                // Create a list of async tasks
                val tasks = (0 until numThreads).map { threadIndex ->
                    async {
                        val simsForThisThread = simulationsPerThread + if (threadIndex < remainder) 1 else 0
                        repeat(simsForThisThread) {
                            if (simulateSingleGame(bettingStrategy)) {
                                successful.increment()
                            } else {
                                failed.increment()
                            }
                        }
                    }
                }
                tasks.awaitAll()
            }

            // Wait for simulation to finish
            simulationJob.join()
            // Stop the UI updater
            uiUpdateJob.cancelAndJoin()

            // Final UI update
            val finalSuccessful = successful.sum().toInt()
            val finalFailed = failed.sum().toInt()
            view.statisticsView.updateStatistics(finalSuccessful, finalFailed)
            view.controlsView.autoSimulateButton.text = "Auto Simulate"
            view.controlsView.autoSimulateButton.isEnabled = true
        }
    }
    
    private fun updateTheoreticalProbability() {
        val strategy = bettingStrategy
        if (strategy is S1BettingStrategy) {
            val betAmount = view.controlsView.betAmountField.text.toIntOrNull() ?: 1
            val probability = analyticsUseCase.calculateWinProbability(
                initialBalance = initialBalance,
                targetWinnings = targetWinnings,
                betAmount = betAmount
            )
            view.statisticsView.updateTheoreticalProbability(probability)
        } else {
            view.statisticsView.updateTheoreticalProbability(null)
        }
    }

    /**
     * Simulates a single complete game until it succeeds or fails.
     * Returns true if the game was successful (reached target), false if failed (ran out of money).
     */
    private fun simulateSingleGame(strategy: BettingStrategy): Boolean {

        return rouletteUseCase.simulateFullGame(
            initialBalance = initialBalance,
            targetWinnings = targetWinnings,
            betColor = betColor,
            strategy = strategy
        )
    }
}
