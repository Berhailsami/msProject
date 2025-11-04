package org.example.feature.roulette.presentation.view.component

import org.example.core.domain.model.roulette.BetColor
import org.example.core.domain.model.roulette.BettingStrategy
import org.example.core.domain.model.roulette.strategy.BoldPlayBettingStrategy
import org.example.core.domain.model.roulette.strategy.S1BettingStrategy
import org.example.core.domain.model.roulette.strategy.MartingaleBettingStrategy
import org.example.presentation.common.RoundedPanel
import org.example.presentation.common.util.IntegerFilter
import java.awt.Color
import java.awt.FlowLayout
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.ButtonGroup
import javax.swing.JButton
import javax.swing.JComboBox
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JRadioButton
import javax.swing.JTextField
import javax.swing.text.AbstractDocument

class ControlsView : RoundedPanel(15) {
    
    val initialBalanceField = JTextField(10)
    val targetWinningsField = JTextField(10)
    val betAmountField = JTextField(10)
    val numSimulationsField = JTextField(10)
    
    val redBetButton = JRadioButton("Red", true)
    val blackBetButton = JRadioButton("Black", false)
    private val betColorGroup = ButtonGroup()
    
    val bettingStrategyComboBox = JComboBox<String>(arrayOf("S1", "Martingale", "Bold Play"))
    
    val initializeButton = JButton("Initialize Game")
    val stepButton = JButton("Step")
    val autoSimulateButton = JButton("Auto Simulate")
    val backButton = JButton("Back")
    val resetButton = JButton("Reset")
    
    init {
        background = Color.WHITE
        layout = GridBagLayout()
        val gbc = GridBagConstraints()
        
        initialBalanceField.text = "0"
        targetWinningsField.text = "0"
        betAmountField.text = "1"
        numSimulationsField.text = "1000000"
        
        // Numeric filters
        (initialBalanceField.document as AbstractDocument).documentFilter = IntegerFilter()
        (targetWinningsField.document as AbstractDocument).documentFilter = IntegerFilter()
        (betAmountField.document as AbstractDocument).documentFilter = IntegerFilter()
        (numSimulationsField.document as AbstractDocument).documentFilter = IntegerFilter()
        
        // Bet color radio buttons
        betColorGroup.add(redBetButton)
        betColorGroup.add(blackBetButton)
        
        // First row: Input fields
        gbc.gridx = 0
        gbc.gridy = 0
        gbc.anchor = GridBagConstraints.WEST
        gbc.insets = java.awt.Insets(5, 5, 5, 5)
        add(JLabel("Initial Balance ($):"), gbc)
        
        gbc.gridx = 1
        gbc.fill = GridBagConstraints.HORIZONTAL
        add(initialBalanceField, gbc)
        
        gbc.gridx = 2
        gbc.fill = GridBagConstraints.NONE
        add(JLabel("Target Winnings ($):"), gbc)
        
        gbc.gridx = 3
        gbc.fill = GridBagConstraints.HORIZONTAL
        add(targetWinningsField, gbc)
        
        // Second row: More input fields
        gbc.gridx = 0
        gbc.gridy = 1
        gbc.fill = GridBagConstraints.NONE
        add(JLabel("Bet Amount ($):"), gbc)
        
        gbc.gridx = 1
        gbc.fill = GridBagConstraints.HORIZONTAL
        add(betAmountField, gbc)
        
        gbc.gridx = 2
        gbc.fill = GridBagConstraints.NONE
        add(JLabel("Simulations:"), gbc)
        
        gbc.gridx = 3
        gbc.fill = GridBagConstraints.HORIZONTAL
        add(numSimulationsField, gbc)
        
        // Third row: Radio buttons and strategy selection
        gbc.gridx = 0
        gbc.gridy = 2
        gbc.fill = GridBagConstraints.NONE
        add(JLabel("Bet Color:"), gbc)
        
        val radioPanel = JPanel(FlowLayout(FlowLayout.LEFT, 5, 0))
        radioPanel.background = Color.WHITE
        radioPanel.add(redBetButton)
        radioPanel.add(blackBetButton)
        
        gbc.gridx = 1
        gbc.gridwidth = 1
        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.anchor = GridBagConstraints.WEST
        add(radioPanel, gbc)
        
        gbc.gridx = 2
        gbc.gridwidth = 1
        gbc.fill = GridBagConstraints.NONE
        add(JLabel("Strategy:"), gbc)
        
        gbc.gridx = 3
        gbc.fill = GridBagConstraints.HORIZONTAL
        add(bettingStrategyComboBox, gbc)
        
        // Fourth row: Action buttons
        gbc.gridx = 0
        gbc.gridy = 3
        gbc.gridwidth = 1
        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.weightx = 0.2
        gbc.anchor = GridBagConstraints.CENTER
        add(initializeButton, gbc)
        
        gbc.gridx = 1
        add(stepButton, gbc)
        
        gbc.gridx = 2
        add(autoSimulateButton, gbc)
        
        gbc.gridx = 3
        add(backButton, gbc)
        
        gbc.gridx = 4
        add(resetButton, gbc)
    }
    
    fun getBetColor(): BetColor {
        return if (redBetButton.isSelected) BetColor.RED else BetColor.BLACK
    }
    
    fun getBettingStrategy(): BettingStrategy {
        val betAmount = betAmountField.text.toIntOrNull() ?: 1
        return when (bettingStrategyComboBox.selectedItem) {
            "S1" -> S1BettingStrategy(betAmount)
            "Martingale" -> MartingaleBettingStrategy(betAmount)
            "Bold Play" -> BoldPlayBettingStrategy()
            else -> S1BettingStrategy(betAmount)
        }
    }
}
