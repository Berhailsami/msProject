package org.example.feature.dog_flea.presentation.view.component

import org.example.presentation.common.RoundedPanel
import org.example.presentation.common.util.IntegerFilter
import java.awt.Color
import java.awt.FlowLayout
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JTextField
import javax.swing.text.AbstractDocument

class ControlsView : RoundedPanel(15) {

    val nFleasDogA = JTextField(10)
    val nFleasDogB = JTextField(10)
    val stepButton = JButton("Step")
    val backButton = JButton("Back")
    val resetButton = JButton("Reset")

    val goToInitialButton = JButton("Go to initial state")

    init {
        background = Color.WHITE
        layout = FlowLayout(FlowLayout.CENTER)

        nFleasDogA.text = "0"
        nFleasDogB.text = "0"

        // Numeric Filter for JTextField
        (nFleasDogA.document as AbstractDocument).documentFilter = IntegerFilter()
        (nFleasDogB.document as AbstractDocument).documentFilter = IntegerFilter()

        // Add components to the panel (this was missing)
        add(JLabel("Fleas on Dog A:"))
        add(nFleasDogA)
        add(JLabel("Fleas on Dog B:"))
        add(nFleasDogB)
        add(stepButton)
        add(backButton)
        add(resetButton)
        add(goToInitialButton)
    }
}