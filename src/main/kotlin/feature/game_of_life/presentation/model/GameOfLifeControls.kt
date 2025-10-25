package org.example.feature.game_of_life.presentation.model

interface GameOfLifeControls {
    fun onStartClicked()
    fun onStopClicked()
    fun onStepClicked()
    fun onBackClicked()
    fun onResetClicked()
    fun onCellClicked(row: Int, col: Int)
}