package org.example.core.domain.use_case

import org.example.core.domain.model.Cell
import org.example.core.domain.model.Grid

class CalculateNextGenerationUseCase {
    fun invoke(currentGrid: Grid): Grid {
        val newCells = mutableListOf<Cell>()
        //TODO: implement the logic to calculate the next generation of the grid
        return Grid(currentGrid.height, currentGrid.width, newCells)
    }
}