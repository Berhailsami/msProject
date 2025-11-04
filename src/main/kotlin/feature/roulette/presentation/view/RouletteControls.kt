package org.example.feature.roulette.presentation.view

import org.example.core.domain.model.roulette.BetColor
import org.example.core.domain.model.roulette.strategy.BettingStrategy

interface RouletteControls {
    fun onInitialBalanceChanged(balance: Int)
    fun onTargetWinningsChanged(target: Int)
    fun onBetAmountChanged(amount: Int)
    fun onBetColorChanged(color: BetColor)
    fun onBettingStrategyChanged(strategy: BettingStrategy)
    fun onInitializeClicked()
    fun onStepClicked()
    fun onAutoSimulateClicked()
    fun onBackClicked()
    fun onResetClicked()
}
