package feature.dfa.presentation.view

import org.example.presentation.common.RoundedPanel
import java.awt.Color
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.*

class DFAVerifierView : RoundedPanel(15) {

    val exampleCombo = JComboBox(arrayOf(
        "Example 1: Even number of a",
        "Example 2: Ends with ab",
        "Example 3: Binary divisible by 3"
    ))
    val loadExampleButton = JButton("Load Example")

    val statesField = JTextField(20)
    val alphabetField = JTextField(20)
    val transitionsArea = JTextArea(6, 24)
    val startStateField = JTextField(10)
    val acceptStatesField = JTextField(20)
    val wordField = JTextField(20)

    val variablesArea = JTextArea(4, 24)

    val simulationArea = JTextArea(8, 24)

    val buildButton = JButton("Build DFA")
    val checkButton = JButton("Check Word")
    val resetButton = JButton("Reset")

    val statusLabel = JLabel("Enter DFA components and press Build")

    init {
        layout = GridBagLayout()
        background = Color.WHITE
        val gbc = GridBagConstraints()
        gbc.gridx = 0
        gbc.gridy = 0
        gbc.anchor = GridBagConstraints.WEST
        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.weightx = 1.0

        /*run {
            val label = JLabel("Examples:")
            val panel = JPanel()
            panel.add(exampleCombo)
            panel.add(loadExampleButton)

            val rowGbc = GridBagConstraints()
            rowGbc.gridx = 0
            rowGbc.gridy = gbc.gridy
            rowGbc.anchor = GridBagConstraints.WEST
            rowGbc.insets = Insets(4, 6, 4, 6)
            add(label, rowGbc)

            val cGbc = GridBagConstraints()
            cGbc.gridx = 1
            cGbc.gridy = gbc.gridy
            cGbc.weightx = 1.0
            cGbc.fill = GridBagConstraints.HORIZONTAL
            cGbc.insets = Insets(4, 6, 4, 6)
            add(panel, cGbc)
            gbc.gridy++
        }*/

        fun addRow(label: String, component: JComponent) {
            val l = JLabel(label)
            val rowGbc = GridBagConstraints()
            rowGbc.gridx = 0
            rowGbc.gridy = gbc.gridy
            rowGbc.anchor = GridBagConstraints.WEST
            rowGbc.insets = Insets(4, 6, 4, 6)
            add(l, rowGbc)

            val cGbc = GridBagConstraints()
            cGbc.gridx = 1
            cGbc.gridy = gbc.gridy
            cGbc.weightx = 1.0
            cGbc.fill = GridBagConstraints.HORIZONTAL
            cGbc.insets = Insets(4, 6, 4, 6)
            add(component, cGbc)
            gbc.gridy++
        }

        transitionsArea.border = BorderFactory.createLineBorder(Color.LIGHT_GRAY)
        transitionsArea.toolTipText = "One transition per line (or separate with ';'). Example: q0,a->q1"

        addRow("States:", statesField)
        addRow("Alphabet:", alphabetField)
        variablesArea.border = BorderFactory.createLineBorder(Color.LIGHT_GRAY)
        variablesArea.toolTipText = "Optional variable definitions. One per line or ';'. Example: d: 0-9; op: +,-,/,*"
        addRow("Alphabet variables (optional):", JScrollPane(variablesArea))
        addRow("Transitions (one per line or ';' separated):", JScrollPane(transitionsArea))
        addRow("Start state:", startStateField)
        addRow("Accept states:", acceptStatesField)
        addRow("Word:", wordField)

        simulationArea.isEditable = false
        simulationArea.border = BorderFactory.createLineBorder(Color.LIGHT_GRAY)
        simulationArea.toolTipText = "Shows the step-by-step state transitions while checking the word"
        addRow("Simulation trace:", JScrollPane(simulationArea))

        val buttonsPanel = JPanel()
        buttonsPanel.add(buildButton)
        buttonsPanel.add(checkButton)
        buttonsPanel.add(resetButton)
        val btnGbc = GridBagConstraints()
        btnGbc.gridx = 0
        btnGbc.gridy = gbc.gridy
        btnGbc.gridwidth = 2
        btnGbc.insets = Insets(8, 6, 8, 6)
        add(buttonsPanel, btnGbc)
        gbc.gridy++

        val statusGbc = GridBagConstraints()
        statusGbc.gridx = 0
        statusGbc.gridy = gbc.gridy
        statusGbc.gridwidth = 2
        statusGbc.weightx = 1.0
        statusGbc.fill = GridBagConstraints.HORIZONTAL
        statusLabel.foreground = Color(0x222222)
        add(statusLabel, statusGbc)
    }
}
