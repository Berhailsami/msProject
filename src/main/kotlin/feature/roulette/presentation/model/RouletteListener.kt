package org.example.feature.roulette.presentation.model

import org.example.core.domain.model.roulette.RouletteGame

interface RouletteListener {
    fun onGameUpdated(game: RouletteGame)
}

