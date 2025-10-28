package org.example.feature.dog_flea.presentation.view.component

import org.example.core.domain.model.dog_flea.Generation
import org.example.feature.dog_flea.presentation.model.DogFleaListener
import org.example.presentation.common.RoundedPanel
import java.awt.Color
import java.awt.Font
import java.awt.GridLayout
import javax.swing.BorderFactory
import javax.swing.JLabel
import javax.swing.SwingUtilities
import javax.swing.border.TitledBorder

class ResultView : RoundedPanel(15), DogFleaListener {

    private val generationValueLabel = JLabel("0")
    private val randomNumberValueLabel = JLabel("0.00")
    private val totalFleasValueLabel = JLabel("0")

    init {
        background = Color.WHITE

        border = BorderFactory.createTitledBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10),
            "Simulation Stats",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            Font(Font.SANS_SERIF, Font.BOLD, 14)
        )

        layout = GridLayout(0, 2, 10, 5)

        add(JLabel("Generation:"))
        add(generationValueLabel)

        add(JLabel("Random Number:"))
        add(randomNumberValueLabel)

        add(JLabel("Total Fleas:"))
        add(totalFleasValueLabel)
    }

    override fun onGenerationCalculated(generation: Generation) {
        SwingUtilities.invokeLater {

            generationValueLabel.text = generation.genNumber.toString()

            randomNumberValueLabel.text = String.format("%.2f", generation.randomNumber)

            totalFleasValueLabel.text = generation.totalFleas.toString()

        }
    }
}