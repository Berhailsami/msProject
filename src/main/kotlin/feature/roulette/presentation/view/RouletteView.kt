package org.example.feature.roulette.presentation.view

import org.example.core.domain.model.roulette.BetColor
import org.example.feature.roulette.presentation.view.component.ControlsView
import org.example.feature.roulette.presentation.view.component.GameView
import org.example.feature.roulette.presentation.view.component.StatisticsView
import org.example.presentation.common.RoundedPanel
import java.awt.Color
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.SwingUtilities
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

class RouletteView : RoundedPanel(15) {
    
    val controlsView = ControlsView()
    val gameView = GameView()
    val statisticsView = StatisticsView()
    
    private var controls: RouletteControls? = null
    
    init {
        layout = GridBagLayout()
        background = Color.WHITE
        val gbc = GridBagConstraints()
        
        // Controls at the top
        gbc.gridx = 0
        gbc.gridy = 0
        gbc.gridwidth = 2
        gbc.weightx = 1.0
        gbc.weighty = 0.0
        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.anchor = GridBagConstraints.NORTH
        gbc.insets = Insets(5, 5, 5, 5)
        add(controlsView, gbc)
        
        // Game view on left
        gbc.gridx = 0
        gbc.gridy = 1
        gbc.gridwidth = 1
        gbc.weightx = 0.7
        gbc.weighty = 1.0
        gbc.fill = GridBagConstraints.BOTH
        gbc.insets = Insets(10, 10, 10, 5)
        add(gameView, gbc)
        
        // Statistics view on right
        gbc.gridx = 1
        gbc.gridy = 1
        gbc.weightx = 0.3
        gbc.weighty = 1.0
        gbc.insets = Insets(10, 5, 10, 10)
        add(statisticsView, gbc)
    }
    
    fun setControls(controls: RouletteControls) {
        SwingUtilities.invokeLater {
            this.controls = controls
            wireControls()
        }
    }
    
    private fun wireControls() {
        // Initial balance field
        controlsView.initialBalanceField.addActionListener {
            val balance = controlsView.initialBalanceField.text.toIntOrNull() ?: return@addActionListener
            controls?.onInitialBalanceChanged(balance)
        }
        controlsView.initialBalanceField.document.addDocumentListener(object : DocumentListener {
            override fun insertUpdate(e: DocumentEvent) { updateBalance() }
            override fun removeUpdate(e: DocumentEvent) { updateBalance() }
            override fun changedUpdate(e: DocumentEvent) { updateBalance() }
            private fun updateBalance() {
                controlsView.initialBalanceField.text.toIntOrNull()?.let {
                    controls?.onInitialBalanceChanged(it)
                }
            }
        })
        
        // Target winnings field
        controlsView.targetWinningsField.addActionListener {
            val target = controlsView.targetWinningsField.text.toIntOrNull() ?: return@addActionListener
            controls?.onTargetWinningsChanged(target)
        }
        controlsView.targetWinningsField.document.addDocumentListener(object : DocumentListener {
            override fun insertUpdate(e: DocumentEvent) { updateTarget() }
            override fun removeUpdate(e: DocumentEvent) { updateTarget() }
            override fun changedUpdate(e: DocumentEvent) { updateTarget() }
            private fun updateTarget() {
                controlsView.targetWinningsField.text.toIntOrNull()?.let {
                    controls?.onTargetWinningsChanged(it)
                }
            }
        })
        
        // Bet amount field
        controlsView.betAmountField.addActionListener {
            val amount = controlsView.betAmountField.text.toIntOrNull() ?: return@addActionListener
            controls?.onBetAmountChanged(amount)
            controls?.onBettingStrategyChanged(controlsView.getBettingStrategy())
        }
        controlsView.betAmountField.document.addDocumentListener(object : DocumentListener {
            override fun insertUpdate(e: DocumentEvent) { updateBetAmount() }
            override fun removeUpdate(e: DocumentEvent) { updateBetAmount() }
            override fun changedUpdate(e: DocumentEvent) { updateBetAmount() }
            private fun updateBetAmount() {
                controlsView.betAmountField.text.toIntOrNull()?.let {
                    controls?.onBetAmountChanged(it)
                    controls?.onBettingStrategyChanged(controlsView.getBettingStrategy())
                }
            }
        })
        
        // Bet color radio buttons
        controlsView.redBetButton.addActionListener {
            controls?.onBetColorChanged(BetColor.RED)
        }
        controlsView.blackBetButton.addActionListener {
            controls?.onBetColorChanged(BetColor.BLACK)
        }
        
        // Betting strategy combo box
        controlsView.bettingStrategyComboBox.addActionListener {
            controls?.onBettingStrategyChanged(controlsView.getBettingStrategy())
        }
        
        // Buttons
        controlsView.initializeButton.addActionListener { controls?.onInitializeClicked() }
        controlsView.stepButton.addActionListener { controls?.onStepClicked() }
        controlsView.autoSimulateButton.addActionListener { controls?.onAutoSimulateClicked() }
        controlsView.backButton.addActionListener { controls?.onBackClicked() }
        controlsView.resetButton.addActionListener { controls?.onResetClicked() }
    }
}
