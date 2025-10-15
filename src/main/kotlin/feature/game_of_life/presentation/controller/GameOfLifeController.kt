package org.example.feature.game_of_life.presentation.controller

import org.example.core.domain.use_case.CalculateNextGenerationUseCase
import org.example.feature.game_of_life.presentation.model.GameOfLifeModel
import org.example.feature.game_of_life.presentation.view.GameOfLifeView

class GameOfLifeController(
    private val gameOfLifeModel: GameOfLifeModel,
    private val gameOfLifeView: GameOfLifeView,
    private val calculateNextGenerationUseCase : CalculateNextGenerationUseCase = CalculateNextGenerationUseCase()
) {
    fun init() {
        gameOfLifeModel.addListener(gameOfLifeView)
        val initialGrid = gameOfLifeModel.grid
        gameOfLifeModel.setGrid(initialGrid)
    }

}