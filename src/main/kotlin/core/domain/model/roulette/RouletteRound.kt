package org.example.core.domain.model.roulette

data class RouletteRound(
    val roundNumber: Int,
    val betAmount: Int,
    val betColor: BetColor,
    val ballColor: BallColor,
    val balanceBefore: Int,
    val balanceAfter: Int,
    val gameResult: Boolean
)

