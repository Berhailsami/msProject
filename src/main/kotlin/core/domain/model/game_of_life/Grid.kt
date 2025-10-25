package org.example.core.domain.model.game_of_life

data class Grid(
    val columns: Int,
    val rows: Int,
    val cells: List<Cell>
)
