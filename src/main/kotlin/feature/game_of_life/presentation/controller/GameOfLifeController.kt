package org.example.feature.game_of_life.presentation.controller

import org.example.core.domain.use_case.CalculateNextGenerationUseCase
import org.example.feature.game_of_life.presentation.model.GameOfLifeModel
import org.example.feature.game_of_life.presentation.view.GameOfLifeView
import javax.swing.Timer

class GameOfLifeController(
    private val gameOfLifeModel: GameOfLifeModel,
    private val calculateNextGenerationUseCase : CalculateNextGenerationUseCase = CalculateNextGenerationUseCase()
) {
    private var gameOfLifeView: GameOfLifeView? = null
    private var timer: Timer? = null
    private var isRunning: Boolean = false

    fun setView(view: GameOfLifeView) {
        this.gameOfLifeView = view
    }

    fun init() {
        gameOfLifeView?.let { view ->
            gameOfLifeModel.addListener(view.gridView)

            val initialGrid = gameOfLifeModel.grid
            gameOfLifeModel.setGrid(initialGrid)

            // Wire controls
            view.controlsView.startButton.addActionListener { start() }
            view.controlsView.stopButton.addActionListener { stop() }
            view.controlsView.stepButton.addActionListener { step() }
            view.controlsView.backButton.addActionListener { back() }
            view.controlsView.resetButton.addActionListener { reset() }
        }
    }

    fun onCellClicked(row: Int, col: Int) {
        gameOfLifeModel.toggleCellState(row, col)
    }

    private fun start() {
        if (isRunning) return
        isRunning = true
        if (timer == null) {
            timer = Timer(100) { step() }
        }
        timer?.start()
    }

    private fun step() {
        val next = calculateNextGenerationUseCase.invoke(gameOfLifeModel.grid)
        gameOfLifeModel.setGrid(next, pushToHistory = true)
    }

    private fun stop() {
        isRunning = false
        timer?.stop()
    }

    private fun back() {
        gameOfLifeModel.stepBack()
    }

    private fun reset() {
        stop()
        gameOfLifeModel.reset()
    }

}