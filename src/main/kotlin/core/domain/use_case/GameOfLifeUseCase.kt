package org.example.core.domain.use_case

import org.example.core.domain.model.game_of_life.Cell
import org.example.core.domain.model.game_of_life.Grid

class GameOfLifeUseCase {
    fun invoke(currentGrid: Grid): Grid {
        val rows = currentGrid.rows
        val columns = currentGrid.columns

        val aliveSet = currentGrid.cells
            .asSequence()
            .filter { it.isAlive }
            .map { it.x to it.y }
            .toSet()

        fun isAliveAt(x: Int, y: Int): Boolean = (x to y) in aliveSet

        fun countAliveNeighbors(x: Int, y: Int): Int {
            var count = 0
            // Periodic boundary conditions
            for (dy in -1..1) {
                for (dx in -1..1) {
                    if (dx == 0 && dy == 0) continue
                    val nx = (x + dx).mod(columns)
                    val ny = (y + dy).mod(rows)
                    if (isAliveAt(nx, ny)) count++
                }
            }
            return count
        }

        val nextCells = ArrayList<Cell>(rows * columns)
        for (y in 0 until rows) {
            for (x in 0 until columns) {
                val alive = isAliveAt(x, y)
                val neighbors = countAliveNeighbors(x, y)
                val nextAlive = when {
                    alive && (neighbors == 2 || neighbors == 3) -> true
                    !alive && neighbors == 3 -> true
                    else -> false
                }
                nextCells.add(Cell(x = x, y = y, isAlive = nextAlive))
            }
        }

        return Grid(columns = columns, rows = rows, cells = nextCells)
    }
}