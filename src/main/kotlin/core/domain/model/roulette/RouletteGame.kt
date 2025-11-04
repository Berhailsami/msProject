package org.example.core.domain.model.roulette

import org.example.core.domain.model.roulette.strategy.BettingStrategy

data class RouletteGame(
    val initialBalance: Int,
    val targetWinnings: Int,
    val currentBalance: Int,
    val totalWinnings: Int,
    val rounds: List<RouletteRound>,
    val isComplete: Boolean,
    val success: Boolean? = null,
    var bettingStrategy: BettingStrategy
) {
    val totalRounds: Int get() = rounds.size
}

