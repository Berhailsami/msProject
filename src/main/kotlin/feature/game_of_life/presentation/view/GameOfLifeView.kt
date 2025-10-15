package org.example.feature.game_of_life.presentation.view

import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.JPanel

class GameOfLifeView(
    onCellClicked: (row: Int, col: Int) -> Unit
) : JPanel() {

    val gridView = GridView(onCellClicked)
    val controlsView = ControlsView()

    init {
        layout = GridBagLayout()
        isOpaque = false
        val gbc = GridBagConstraints()

        gbc.gridx = 0
        gbc.gridy = 0
        gbc.weightx = 1.0
        gbc.weighty = 0.0
        gbc.anchor = GridBagConstraints.NORTH
        gbc.fill = GridBagConstraints.HORIZONTAL
        add(controlsView, gbc)

        gbc.gridy = 1
        gbc.weightx = 0.0
        gbc.weighty = 0.0
        gbc.anchor = GridBagConstraints.CENTER
        gbc.fill = GridBagConstraints.NONE
        add(gridView, gbc)
    }
}