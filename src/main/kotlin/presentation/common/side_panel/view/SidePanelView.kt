package org.example.presentation.common.side_panel.view

import org.example.presentation.common.RoundedPanel
import java.awt.Color
import javax.swing.BorderFactory
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel

class SidePanelView : RoundedPanel(15) {

    val titleLabel = JLabel("Navigation")
    val gameOfLifeButton = JButton("Game of Life")
    val dogFleaButton = JButton("Dog Flea")

    init {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)
        background = Color.WHITE
        border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
        titleLabel.font = titleLabel.font.deriveFont(20.0f)
        add(titleLabel)
        add(Box.createVerticalStrut(10))
        add(gameOfLifeButton)
        add(dogFleaButton)
    }
}