package org.example.feature.game_of_life.presentation.view

import org.example.presentation.common.RoundedPanel
import java.awt.FlowLayout
import javax.swing.JButton

class TestView : RoundedPanel(15){
    val startButton = JButton("Start")
    val stopButton = JButton("Stop")
    val stepButton = JButton("Step")
    val resetButton = JButton("Reset")

    init {
        // A simple layout to arrange buttons in a row
        layout = FlowLayout(FlowLayout.CENTER)

        add(startButton)
        add(stopButton)
        add(stepButton)
        add(resetButton)
    }
}