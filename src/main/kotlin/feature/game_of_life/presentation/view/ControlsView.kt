package org.example.feature.game_of_life.presentation.view

import org.example.presentation.common.RoundedPanel
import java.awt.FlowLayout
import javax.swing.JButton

class ControlsView : RoundedPanel(15){
    val startButton = JButton("Start")
    val stopButton = JButton("Stop")
    val stepButton = JButton("Step")
    val backButton = JButton("Back")
    val resetButton = JButton("Reset")

    init {

        layout = FlowLayout(FlowLayout.CENTER)

        add(startButton)
        add(stopButton)
        add(stepButton)
        add(backButton)
        add(resetButton)
    }
}