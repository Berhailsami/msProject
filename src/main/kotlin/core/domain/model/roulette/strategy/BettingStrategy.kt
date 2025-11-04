package org.example.core.domain.model.roulette.strategy

import org.example.core.domain.model.roulette.RouletteRound

interface BettingStrategy {
    fun nextBet(lastRound: RouletteRound?): Int
}