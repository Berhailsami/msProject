package org.example.feature.game_of_life.presentation.model

import org.example.core.domain.model.Grid

class GameOfLifeModel {

    var grid: Grid = Grid(30, 50, emptyList())
        private set

    private val listeners = mutableListOf<GameOfLifeListener>()

    fun addListener(listener: GameOfLifeListener) {
        listeners.add(listener)
    }

    private fun notifyListeners() {
        listeners.forEach {listener ->
            //TODO: functions of the interface
        }
    }
}