package org.example.feature.game_of_life.presentation.controller

import org.example.core.domain.use_case.GameOfLifeUseCase
import org.example.feature.game_of_life.presentation.view.GameOfLifeControls
import org.example.feature.game_of_life.presentation.model.GameOfLifeModel
import org.example.feature.game_of_life.presentation.view.GameOfLifeView
import javax.swing.SwingUtilities
import javax.swing.Timer

class GameOfLifeController(
    private val model: GameOfLifeModel,
    private val view: GameOfLifeView,
    private val useCase: GameOfLifeUseCase = GameOfLifeUseCase()
) : GameOfLifeControls {

    private var timer: Timer? = null
    private var isRunning = false

    init {
        view.setControls(this)

        model.addListener(view.gridView)

        model.setGrid(model.grid)
    }

    override fun onStartClicked() {
        SwingUtilities.invokeLater {
            if (isRunning) return@invokeLater
            isRunning = true
            if (timer == null) {
                timer = Timer(100) { onStepClicked() }
            }
            timer?.start()
        }
    }

    override fun onStopClicked() {
        SwingUtilities.invokeLater {
            isRunning = false
            timer?.stop()
        }
    }

    override fun onStepClicked() {
        SwingUtilities.invokeLater {
            val next = useCase.invoke(model.grid)
            model.setGrid(next, pushToHistory = true)
        }
    }

    override fun onBackClicked() {
        SwingUtilities.invokeLater {
            model.stepBack()
        }
    }

    override fun onResetClicked() {
        SwingUtilities.invokeLater {
            onStopClicked()
            model.reset()
        }
    }

    override fun onCellClicked(row: Int, col: Int) {
        SwingUtilities.invokeLater {
            model.toggleCellState(row, col)
        }
    }
}
