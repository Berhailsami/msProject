package org.example.core.domain.model.roulette.strategy

import org.example.core.domain.model.roulette.RouletteRound

class S2BettingStrategy(private val initialBet: Int = 1) : BettingStrategy {
    private var currentBet = initialBet
    override fun nextBet(lastRound: RouletteRound?): Int {
        return if (lastRound == null) {
            initialBet
        } else if (lastRound.gameResult) {
            currentBet = initialBet
            initialBet
        } else {
            currentBet *= 2
            currentBet
        }
    }
}
