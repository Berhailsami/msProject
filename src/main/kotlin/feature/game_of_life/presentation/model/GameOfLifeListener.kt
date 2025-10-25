package org.example.feature.game_of_life.presentation.model

import org.example.core.domain.model.game_of_life.Grid

interface GameOfLifeListener {
    fun onGridUpdated(grid: Grid)
}