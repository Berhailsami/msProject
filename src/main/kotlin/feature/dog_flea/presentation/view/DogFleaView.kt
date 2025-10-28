package org.example.feature.dog_flea.presentation.view

import org.example.feature.dog_flea.presentation.view.component.ControlsView
import org.example.feature.dog_flea.presentation.view.component.DogView
import org.example.feature.dog_flea.presentation.view.component.ResultView
import org.example.presentation.common.RoundedPanel
import java.awt.Color
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.SwingUtilities
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

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
    val resultView = ResultView()


    private var controls: DogFleaControls? = null

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

        gbc.gridx = 0
        gbc.gridy = 2
        gbc.gridwidth = 2
        gbc.weightx = 1.0
        gbc.weighty = 1.0
        gbc.fill = GridBagConstraints.NONE
        gbc.anchor = GridBagConstraints.CENTER
        gbc.insets = Insets(10, 10, 10, 10)
        add(resultView, gbc)
    }

    fun setControls(controls: DogFleaControls){
        SwingUtilities.invokeLater {
            this.controls = controls
            wireControls()
        }
    }

    private fun wireControls() {

        fun updateDogFleas(dogName: String, text: String) {
            val fleas = text.toIntOrNull() ?: return
            controls?.onDogFleasChanged(dogName, fleas)
        }
        fun updateIfValid(dog: String,text:String) {
            if (text.isNotEmpty()) {
                updateDogFleas(dog, text)
            }
        }

        controlsView.nFleasDogA.addActionListener {
            updateDogFleas("A", controlsView.nFleasDogA.text)
        }
        controlsView.nFleasDogB.addActionListener {
            updateDogFleas("B", controlsView.nFleasDogB.text)
        }

        controlsView.nFleasDogA.document.addDocumentListener(object : DocumentListener {
            override fun insertUpdate(e: DocumentEvent) {
                updateIfValid("A", controlsView.nFleasDogA.text)
                updateIfValid("B",controlsView.nFleasDogB.text)
            }
            override fun removeUpdate(e: DocumentEvent) {
                updateIfValid("A", controlsView.nFleasDogA.text)
                updateIfValid("B",controlsView.nFleasDogB.text)
            }
            override fun changedUpdate(e: DocumentEvent) {
                updateIfValid("A", controlsView.nFleasDogA.text)
                updateIfValid("B",controlsView.nFleasDogB.text)
            }
        })

        controlsView.nFleasDogB.document.addDocumentListener(object : DocumentListener {
            override fun insertUpdate(e: DocumentEvent) {
                updateIfValid("B",controlsView.nFleasDogB.text)
                updateIfValid("A", controlsView.nFleasDogA.text)
            }
            override fun removeUpdate(e: DocumentEvent) {
                updateIfValid("B", controlsView.nFleasDogB.text)
                updateIfValid("A", controlsView.nFleasDogA.text)
            }
            override fun changedUpdate(e: DocumentEvent) {
                updateIfValid("B", controlsView.nFleasDogB.text)
                updateIfValid("A", controlsView.nFleasDogA.text)
            }
        })
        
        controlsView.stepButton.addActionListener { controls?.onStepClicked() }
        controlsView.backButton.addActionListener { controls?.onBackClicked() }
        controlsView.resetButton.addActionListener { controls?.onResetClicked() }
        controlsView.goToInitialButton.addActionListener { controls?.onGoToInitialClicked()}
    }
}