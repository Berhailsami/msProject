package org.example.feature.roulette.presentation.view.component

import org.example.presentation.common.RoundedPanel
import java.awt.Color
import java.awt.Font
import java.awt.GridLayout
import javax.swing.BorderFactory
import javax.swing.JLabel
import javax.swing.border.TitledBorder

class StatisticsView : RoundedPanel(15) {
    
    private val probabilityLabel = JLabel("Success Probability: -")
    private val theoreticalProbabilityLabel = JLabel("Theoretical Probability: -")
    private val totalSimulationsLabel = JLabel("Total Simulations: 0")
    private val successfulSimulationsLabel = JLabel("Successful: 0")
    private val failedSimulationsLabel = JLabel("Failed: 0")
    
    init {
        background = Color.WHITE
        
        border = BorderFactory.createTitledBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10),
            "Simulation Statistics",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            Font(Font.SANS_SERIF, Font.BOLD, 14)
        )
        
        layout = GridLayout(5, 1, 10, 10)
        
        probabilityLabel.font = Font(Font.SANS_SERIF, Font.BOLD, 20)
        probabilityLabel.foreground = Color.BLUE.darker()
        
        theoreticalProbabilityLabel.font = Font(Font.SANS_SERIF, Font.ITALIC, 16)
        theoreticalProbabilityLabel.foreground = Color.GRAY
        
        totalSimulationsLabel.font = Font(Font.SANS_SERIF, Font.PLAIN, 14)
        successfulSimulationsLabel.font = Font(Font.SANS_SERIF, Font.PLAIN, 14)
        successfulSimulationsLabel.foreground = Color.GREEN.darker()
        failedSimulationsLabel.font = Font(Font.SANS_SERIF, Font.PLAIN, 14)
        failedSimulationsLabel.foreground = Color.RED.darker()
        
        add(probabilityLabel)
        add(theoreticalProbabilityLabel)
        add(totalSimulationsLabel)
        add(successfulSimulationsLabel)
        add(failedSimulationsLabel)
    }
    
    fun updateStatistics(successful: Int, failed: Int) {
        val total = successful + failed
        if (total == 0) {
            probabilityLabel.text = "Success Probability: -"
            totalSimulationsLabel.text = "Total Simulations: 0"
            successfulSimulationsLabel.text = "Successful: 0"
            failedSimulationsLabel.text = "Failed: 0"
        } else {
            val probability = (successful.toDouble() / total.toDouble()) * 100.0
            val format = if (probability == 0.0 || probability == 100.0) {
                "%.0f%%"
            } else if (probability !in 0.01..99.99) {
                "%.4f%%"
            } else {
                "%.6f%%"
            }
            println("Success Probability: %.16f%%".format(probability))

            probabilityLabel.text = String.format("Success Probability: $format", probability)
            totalSimulationsLabel.text = "Total Simulations: $total"
            successfulSimulationsLabel.text = "Successful: $successful"
            failedSimulationsLabel.text = "Failed: $failed"
        }
    }

    fun updateTheoreticalProbability(probability: Double?) {
        if (probability == null) {
            theoreticalProbabilityLabel.text = "Theoretical Probability: N/A (for S1 only)"
        } else {
            val percentage = probability * 100.0
            theoreticalProbabilityLabel.text = String.format("Theoretical Probability: %.6f%%", percentage)
        }
    }
}
