package org.example.core.domain.use_case

import kotlin.math.pow

class RouletteApproximativeProbability {

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

        val result = (ratio / (1.0 - ratio )).pow(targetUnits)

        return if (result == 0.0) 0.0 else result
    }
}
