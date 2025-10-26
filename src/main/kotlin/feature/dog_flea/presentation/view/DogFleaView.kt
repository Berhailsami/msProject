package org.example.feature.dog_flea.presentation.view

import org.example.feature.dog_flea.presentation.view.component.ControlsView
import org.example.feature.dog_flea.presentation.view.component.DogView
import org.example.presentation.common.RoundedPanel
import java.awt.Color
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets

class DogFleaView : RoundedPanel(15) {
    val controlsView = ControlsView()
    val dogA = DogView(
        imagePath = "dog.com.png",
        dogName = "A"
    )
    val dogB = DogView(
        imagePath = "dog.com.png",
        dogName = "B"
    )
    private var controls: ControlsView? = null

    init {

        layout = GridBagLayout()
        background = Color.WHITE
        val gbc = GridBagConstraints()

        gbc.gridx = 0
        gbc.gridy = 0
        gbc.gridwidth = 2
        gbc.weightx = 1.0
        gbc.weighty = 0.0
        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.anchor = GridBagConstraints.NORTH
        gbc.insets = Insets(5, 5, 5, 5)
        add(controlsView, gbc)

        gbc.gridwidth = 1
        gbc.gridx = 0
        gbc.gridy = 1
        gbc.weightx = 0.5
        gbc.weighty = 1.0
        gbc.fill = GridBagConstraints.NONE
        gbc.anchor = GridBagConstraints.CENTER
        gbc.insets = Insets(10, 10, 10, 10)
        add(dogA, gbc)

        gbc.gridx = 1
        gbc.gridy = 1
        gbc.weightx = 0.5
        gbc.weighty = 1.0
        gbc.fill = GridBagConstraints.NONE
        gbc.anchor = GridBagConstraints.CENTER
        gbc.insets = Insets(10, 10, 10, 10)
        add(dogB, gbc)
    }

    fun setControls(controls: DogFleaControls){

    }
}