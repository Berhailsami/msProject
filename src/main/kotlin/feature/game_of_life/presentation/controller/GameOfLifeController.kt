package org.example.feature.game_of_life.presentation.controller

import org.example.core.domain.use_case.CalculateNextGenerationUseCase
import org.example.feature.game_of_life.presentation.model.GameOfLifeModel
import org.example.feature.game_of_life.presentation.view.GameOfLifeView

class GameOfLifeController(
    private val gameOfLifeModel: GameOfLifeModel,
    private val calculateNextGenerationUseCase : CalculateNextGenerationUseCase = CalculateNextGenerationUseCase()
) {
    private var gameOfLifeView: GameOfLifeView? = null

    fun setView(view: GameOfLifeView) {
        this.gameOfLifeView = view
    }

    fun init() {
        gameOfLifeView?.let { view ->
            gameOfLifeModel.addListener(view.gridView)

            val initialGrid = gameOfLifeModel.grid
            gameOfLifeModel.setGrid(initialGrid)
        }
    }

    fun onCellClicked(row: Int, col: Int) {
        gameOfLifeModel.toggleCellState(row, col)
    }

}