package org.example.feature.game_of_life.presentation.view

import org.example.feature.game_of_life.presentation.model.GameOfLifeControls
import org.example.presentation.common.RoundedPanel
import java.awt.GridBagConstraints
import java.awt.GridBagLayout

class GameOfLifeView: RoundedPanel(15) {

    val gridView = GridView()
    val controlsView = ControlsView()
    private var controls: GameOfLifeControls? = null

    init {
        layout = GridBagLayout()
        isOpaque = false
        val gbc = GridBagConstraints()

        gbc.gridx = 0
        gbc.gridy = 0
        gbc.weightx = 1.0
        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.anchor = GridBagConstraints.NORTH

        add(controlsView,gbc)

        gbc.gridy = 1
        gbc.weightx = 1.0
        gbc.weighty = 1.0
        gbc.fill = GridBagConstraints.NONE
        gbc.anchor = GridBagConstraints.CENTER
        add(gridView,gbc)
    }

    fun setControls(controls: GameOfLifeControls) {
        this.controls = controls
        gridView.onCellClicked = controls::onCellClicked
        wireControls()
    }

    private fun wireControls() {
        controlsView.startButton.addActionListener { controls?.onStartClicked() }
        controlsView.stopButton.addActionListener { controls?.onStopClicked() }
        controlsView.stepButton.addActionListener { controls?.onStepClicked() }
        controlsView.backButton.addActionListener { controls?.onBackClicked() }
        controlsView.resetButton.addActionListener { controls?.onResetClicked() }
    }
}