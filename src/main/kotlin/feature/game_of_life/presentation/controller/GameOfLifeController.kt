package org.example.feature.game_of_life.presentation.controller

import org.example.core.domain.use_case.GameOfLifeUseCase
import org.example.feature.game_of_life.presentation.view.GameOfLifeControls
import org.example.feature.game_of_life.presentation.model.GameOfLifeModel
import org.example.feature.game_of_life.presentation.view.GameOfLifeView
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
        if (isRunning) return
        isRunning = true
        if (timer == null) {
            timer = Timer(100) { onStepClicked() }
        }
        timer?.start()
    }

    override fun onStopClicked() {
        isRunning = false
        timer?.stop()
    }

    override fun onStepClicked() {
        val next = useCase.invoke(model.grid)
        model.setGrid(next, pushToHistory = true)
    }

    override fun onBackClicked() {
        model.stepBack()
    }

    override fun onResetClicked() {
        onStopClicked()
        model.reset()
    }

    override fun onCellClicked(row: Int, col: Int) {
        model.toggleCellState(row, col)
    }
}
