package org.example.feature.game_of_life.presentation.controller

import org.example.feature.game_of_life.presentation.model.GameOfLifeModel
import org.example.feature.game_of_life.presentation.view.GameOfLifeView

class GameOfLifeController(
    private val gameOfLifeModel: GameOfLifeModel,
    private val gameOfLifeView: GameOfLifeView
) {
    fun init() {
        gameOfLifeModel.addListener(gameOfLifeView)
        //TODO: add action listeners to the view
    }

}