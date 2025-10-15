package org.example.feature.game_of_life.presentation.view

import org.example.core.domain.model.Grid
import org.example.feature.game_of_life.presentation.model.GameOfLifeListener
import org.example.presentation.common.RoundedPanel
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.SwingUtilities

class GameOfLifeView : RoundedPanel(15), GameOfLifeListener {
    private var grid: Grid? = null
    private val cellSize = 20

    init {
        this.background = Color.WHITE
    }

    override fun getPreferredSize(): Dimension {
        grid?.let {
            val width = it.columns * cellSize
            val height = it.rows * cellSize
            return Dimension(width, height)
        }
        return super.getPreferredSize()
    }

    override fun onGridUpdated(grid: Grid) {
        SwingUtilities.invokeLater {
            this.grid = grid
            this.revalidate()
            this.repaint()
        }
    }

    override fun paintComponent(graphics: Graphics) {
        super.paintComponent(graphics)

        val currentGrid = grid ?: return

        // 3. The drawing logic now uses the fixed cellSize.
        currentGrid.cells.forEach { cell ->
            if (cell.isAlive) {
                graphics.color = Color.GREEN
            } else {
                graphics.color = Color.LIGHT_GRAY
            }
            // Use the fixed cell size for drawing the rectangle
            graphics.fillRect(
                cell.x * cellSize,
                cell.y * cellSize,
                cellSize,
                cellSize
            )
            // Optionally draw an outline for each cell
            graphics.color = Color.decode("#dcdcdc") // A slightly darker gray
            graphics.drawRect(
                cell.x * cellSize,
                cell.y * cellSize,
                cellSize,
                cellSize
            )
        }
    }
}