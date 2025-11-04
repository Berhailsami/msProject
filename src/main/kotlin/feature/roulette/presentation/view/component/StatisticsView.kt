package org.example.feature.roulette.presentation.view.component

import org.example.presentation.common.RoundedPanel
import java.awt.Color
import java.awt.Font
import java.awt.GridLayout
import java.text.DecimalFormat // Import
import javax.swing.BorderFactory
import javax.swing.JLabel
import javax.swing.border.TitledBorder
import kotlin.math.abs    // Import
import kotlin.math.floor  // Import
import kotlin.math.log10  // Import
import kotlin.math.pow    // Import

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
            val rawProbability = successful.toDouble() / total.toDouble()

            val fractionFormat = formatProbabilityAsFraction(rawProbability)

            val percentage = rawProbability * 100.0
            val percentageFormat = String.format("%.8f%%", percentage)

            println("Success Probability: %.16f%%".format(percentage))

            probabilityLabel.text = "Success Probability: $fractionFormat ($percentageFormat)"

            totalSimulationsLabel.text = "Total Simulations: $total"
            successfulSimulationsLabel.text = "Successful: $successful"
            failedSimulationsLabel.text = "Failed: $failed"
        }
    }

    /**
     * Updates the theoretical probability label with a special fraction
     * format for small numbers (e.g., "5/10^5") and a percentage.
     */
    fun updateTheoreticalProbability(probability: Double?) {
        if (probability == null) {
            theoreticalProbabilityLabel.text = "Theoretical Probability: N/A (for S1 only)"
        } else {
            // 1. Get the special fraction format
            val fractionFormat = formatProbabilityAsFraction(probability)

            // 2. Get the percentage format
            val percentage = probability * 100.0
            val percentageFormat = String.format("%.8f%%", percentage)

            // 3. Combine them in the label
            theoreticalProbabilityLabel.text = "Theoretical Probability: $fractionFormat ($percentageFormat)"
        }
    }

    /**
     * Helper function to format a Double into a fractional scientific notation string.
     */
    private fun formatProbabilityAsFraction(prob: Double): String {
        // Handle 0.0
        if (prob == 0.0) {
            return "0"
        }

        val df = DecimalFormat("0.####")
        val exponentLog = floor(log10(prob))

        // If prob >= 1.0 (e.g., 100% success)
        if (exponentLog >= 0) {
            // Special case for 1.0, show "1" instead of "1.0"
            return if (prob == 1.0) "1" else df.format(prob)
        }

        val denominatorExponent = abs(exponentLog.toInt())
        val numerator = prob * (10.0.pow(denominatorExponent))
        val formattedNumerator = df.format(numerator)

        return "$formattedNumerator/10^$denominatorExponent"
    }
}