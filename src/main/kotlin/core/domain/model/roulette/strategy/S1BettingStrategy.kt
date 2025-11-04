package org.example.core.domain.model.roulette.strategy

import org.example.core.domain.model.roulette.strategy.BettingStrategy
import org.example.core.domain.model.roulette.RouletteRound

class S1BettingStrategy(private val betAmount: Int = 1) : BettingStrategy {
    override fun nextBet(lastRound: RouletteRound?): Int {
        return betAmount
    }
}
