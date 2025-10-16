package org.example.feature.game_of_life.presentation.view

import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.BoxLayout
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
        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.anchor = GridBagConstraints.NORTH

        add(controlsView,gbc)

        gbc.gridy = 1
        gbc.weightx = 1.0
        gbc.weighty = 1.0
        gbc.fill = GridBagConstraints.NONE
        gbc.anchor = GridBagConstraints.CENTER
        add(gridView,gbc)
    }
}