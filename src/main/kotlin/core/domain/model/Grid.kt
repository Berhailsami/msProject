package org.example.core.domain.model

data class Grid(
    val columns: Int,
    val rows: Int,
    val cells: List<Cell>
)
