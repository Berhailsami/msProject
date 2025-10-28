package org.example.feature.dog_flea.presentation.controller

import org.example.core.domain.use_case.DogFleaUseCase
import org.example.feature.dog_flea.presentation.model.DogFleaModel
import org.example.feature.dog_flea.presentation.view.DogFleaControls
import org.example.feature.dog_flea.presentation.view.DogFleaView
import java.util.Random
import javax.swing.SwingUtilities

class DogFleaController (
    private val model: DogFleaModel,
    private val view: DogFleaView,
    private val dogFleaUseCase: DogFleaUseCase = DogFleaUseCase()
) : DogFleaControls{

    init {
        view.setControls(this)
        model.addListener(view.dogA)
        model.addListener(view.dogB)
        model.addListener(view.resultView)

    }

    override fun onDogFleasChanged(dogName: String, fleasNewNumber: Int) {
        SwingUtilities.invokeLater {
            model.onDogFleasChanged(dogName, fleasNewNumber)
        }
    }


    override fun onStepClicked() {
        SwingUtilities.invokeLater {
            if(model.generation.totalFleas == 0) return@invokeLater
            val randomNumber = getUniformlyDistributedRandom()
            val newGeneration = dogFleaUseCase.invoke(
                currentGeneration = model.generation.copy(
                    randomNumber = randomNumber
                )
            )
            println("DEBUG: DogFleaController -> onStepClicked : ${newGeneration.dogs}")
            model.setGeneration(
                newGeneration,
                pushToHistory = true
            )
        }
    }

    fun getNormallyDistributedRandom(): Double {
        val standardGaussian = Random().nextGaussian()
        /*
        Mean 0.5,
        StdDev 0.167,
        Range [0, 1]
        */
        return (standardGaussian * 0.167 + 0.5).coerceIn(0.0, 1.0)
    }

    fun getUniformlyDistributedRandom(): Double = Random().nextDouble()

    override fun onBackClicked() {
        SwingUtilities.invokeLater {
            model.stepBack()
        }
    }

    override fun onResetClicked() {
        SwingUtilities.invokeLater {
            model.reset()
        }
    }

}