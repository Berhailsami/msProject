package org.example.feature.roulette.presentation.view.component

import org.example.core.domain.model.roulette.RouletteGame
import org.example.core.domain.model.roulette.RouletteRound
import org.example.feature.roulette.presentation.model.RouletteListener
import org.example.presentation.common.RoundedPanel
import java.awt.Color
import java.awt.Font
import javax.swing.BorderFactory
import javax.swing.JLabel
import javax.swing.JScrollPane
import javax.swing.JTextArea
import javax.swing.SwingUtilities
import javax.swing.border.TitledBorder

class GameView : RoundedPanel(15), RouletteListener {
    
    private val balanceLabel = JLabel("Balance: $0")
    private val targetLabel = JLabel("Target: $0")
    private val winningsLabel = JLabel("Winnings: $0")
    private val statusLabel = JLabel("Status: Not started")
    private val roundsArea = JTextArea(10, 40)
    
    init {
        background = Color.WHITE
        
        border = BorderFactory.createTitledBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10),
            "Game Status",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            Font(Font.SANS_SERIF, Font.BOLD, 14)
        )
        
        layout = java.awt.BorderLayout(10, 10)
        
        balanceLabel.font = Font(Font.SANS_SERIF, Font.BOLD, 16)
        targetLabel.font = Font(Font.SANS_SERIF, Font.BOLD, 16)
        winningsLabel.font = Font(Font.SANS_SERIF, Font.BOLD, 16)
        statusLabel.font = Font(Font.SANS_SERIF, Font.ITALIC, 14)
        
        roundsArea.isEditable = false
        roundsArea.font = Font(Font.MONOSPACED, Font.PLAIN, 12)
        roundsArea.background = Color(245, 245, 245)
        
        val infoPanel = RoundedPanel(15).apply {
            border = BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),

            )
            layout = java.awt.GridLayout(4, 1, 5, 5)
            add(balanceLabel)
            add(targetLabel)
            add(winningsLabel)
            add(statusLabel)
        }
        
        add(infoPanel, java.awt.BorderLayout.NORTH)
        add(JScrollPane(roundsArea), java.awt.BorderLayout.CENTER)
    }
    
    override fun onGameUpdated(game: RouletteGame) {
        SwingUtilities.invokeLater {
            balanceLabel.text = "Balance: $${game.currentBalance}"
            targetLabel.text = "Target: $${game.targetWinnings}"
            winningsLabel.text = "Winnings: $${game.totalWinnings}"
            
            statusLabel.text = when {
                game.isComplete && game.success == true -> "Status: SUCCESS - Target reached!"
                game.isComplete && game.success == false -> "Status: FAILED - No balance remaining"
                game.rounds.isEmpty() -> "Status: Not started"
                else -> "Status: In progress (Round ${game.totalRounds})"
            }
            
            statusLabel.foreground = when {
                game.isComplete && game.success == true -> Color.GREEN.darker()
                game.isComplete && game.success == false -> Color.RED.darker()
                else -> Color.BLACK
            }
            
            // Update rounds display
            val roundsText = buildString {
                appendLine("Round | Bet | Color | Ball | Balance Before | Balance After | Result")
                appendLine("${"-".repeat(80)}")
                
                game.rounds.forEach { round ->
                    appendLine(formatRound(round))
                }
                
                if (game.rounds.isEmpty()) {
                    appendLine("No rounds played yet")
                }
            }
            
            roundsArea.text = roundsText
            roundsArea.caretPosition = roundsArea.document.length // Scroll to bottom
        }
    }
    
    private fun formatRound(round: RouletteRound): String {
        val result = if (round.gameResult) "WON" else "LOST"
        return String.format(
            "%5d | %3d$ | %5s | %4s | %13d$ | %14d$ | %s",
            round.roundNumber,
            round.betAmount,
            round.betColor.name,
            round.ballColor.name,
            round.balanceBefore,
            round.balanceAfter,
            result
        )
    }
}
