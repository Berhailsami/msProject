package org.example.feature.dog_flea.presentation.view

import org.example.presentation.common.RoundedPanel
import java.awt.Color
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.JPanel

class DogFleaView : RoundedPanel(15) {
    val controlsView = ControlsView()
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
    }
}