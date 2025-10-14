package org.example.feature.game_of_life.presentation.model

class GameOfLifeModel {
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