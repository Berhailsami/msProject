package org.example.feature.game_of_life.presentation.model

import org.example.core.domain.model.Cell
import org.example.core.domain.model.Grid

class GameOfLifeModel {

    var grid: Grid = createEmptyGrid()
        private set

    private val history = ArrayDeque<Grid>()
    private val listeners = mutableListOf<GameOfLifeListener>()

    fun addListener(listener: GameOfLifeListener) {
        listeners.add(listener)
    }

    fun setGrid(grid: Grid, pushToHistory: Boolean = false) {
        if (pushToHistory) {
            history.addLast(this.grid)
        }
        this.grid = grid
        notifyListeners()
    }
    private fun notifyListeners() {
        listeners.forEach { listener ->
            listener.onGridUpdated(grid)
        }
    }

    private fun createEmptyGrid(
        rows: Int = 30,
        columns: Int = 50
    ): Grid {

        val initialCells = mutableListOf<Cell>()

        for (y in 0 until rows) {
            for (x in 0 until columns) {
                initialCells.add(
                    Cell(
                        x = x,
                        y = y,
                        isAlive = false
                    )
                )
            }
        }
        return Grid(
            rows = rows,
            columns = columns,
            cells = initialCells
        )
    }

    fun toggleCellState(row: Int, col: Int) {
        val targetCell = grid.cells.find { it.y == row && it.x == col } ?: return

        val updatedCells = grid.cells.map { cell ->
            if (cell == targetCell) {
                cell.copy(
                    isAlive = !cell.isAlive
                )
            } else {
                cell
            }
        }

        setGrid(
            grid.copy(
                cells = updatedCells
            ),
            pushToHistory = true
        )
    }

    fun reset() {
        history.clear()
        setGrid(createEmptyGrid())
    }

    fun stepBack() {
        if (history.isNotEmpty()) {
            val previous = history.removeLast()
            setGrid(previous)
        }
    }
}