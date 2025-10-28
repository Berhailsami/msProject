package org.example.feature.dog_flea.presentation.model

import org.example.core.domain.model.dog_flea.Dog
import org.example.core.domain.model.dog_flea.Generation
import javax.swing.SwingUtilities

class DogFleaModel {

    private val listeners = mutableListOf<DogFleaListener>()

    var generation = Generation(
        0,
        0.0,
        0,
        listOf(
            Dog("A", 0),
            Dog("B", 0)
        )
    )
        private set

    private val history = ArrayDeque<Generation>()

    fun addListener(listener: DogFleaListener) {
        listeners.add(listener)
    }

    fun notifyListeners() {
        SwingUtilities.invokeLater {
            listeners.forEach { listener ->
                listener.onGenerationCalculated(generation)
            }
        }
    }

    fun setGeneration(generation: Generation,pushToHistory: Boolean = false) {
        if (pushToHistory){
            history.addLast(this.generation)
        }
        this.generation = generation
        notifyListeners()
    }

    fun onDogFleasChanged(dogName:String, fleasNewNumber: Int) {
        val updatedDogs = generation.dogs.map { dog ->
            if (dog.name == dogName) {
                dog.copy(
                    fleasNumber = fleasNewNumber
                )
            } else {
                dog
            }
        }

        val newGeneration = generation.copy(
            genNumber = 0,
            randomNumber = 0.0,
            dogs = updatedDogs,
            totalFleas = updatedDogs.sumOf { it.fleasNumber }
        )

        history.clear()

        setGeneration(
            newGeneration,
            pushToHistory = true
        )
    }

    fun reset() {
        history.clear()
        setGeneration(
            Generation(
                0,
                0.0,
                0,
                listOf(
                    Dog(
                        "A",
                        0
                    ),
                    Dog(
                        "B",
                        0
                    )
                )
            )
        )
    }


    fun stepBack() {
            if (history.isNotEmpty()) {
                val previous = history.removeLast()
                setGeneration(previous)
            }
    }
}