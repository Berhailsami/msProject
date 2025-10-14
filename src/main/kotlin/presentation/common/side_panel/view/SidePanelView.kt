package org.example.presentation.common.side_panel.view

import org.example.presentation.common.RoundedPanel
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel

class SidePanelView : RoundedPanel(15) {

    val gameOfLifeButton = JButton("Game of Life")
    val anotherScreenButton = JButton("Another Screen")

    init {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)
        add(JLabel("Navigation"))
        add(gameOfLifeButton)
        add(anotherScreenButton)
    }
}