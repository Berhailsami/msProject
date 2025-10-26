package org.example.feature.dog_flea.presentation.model

import org.example.core.domain.model.dog_flea.Generation
import org.example.core.domain.model.game_of_life.Grid

class DogFleaModel {

    private val listeners = mutableListOf<DogFleaListener>()

    var generation = Generation(
        0,
        0.0,
        0,
        emptyList()
    )
        private set

    private val history = ArrayDeque<Generation>()

    fun addListener(listener: DogFleaListener) {
        listeners.add(listener)
    }

    fun notifyListeners() {
        listeners.forEach { listener ->
            listener.onGenerationCalculated(generation)
        }
    }

    fun setGeneration(generation: Generation,pushToHistory: Boolean = false) {
        if (pushToHistory){
            history.addLast(this.generation)
        }
        this.generation = generation
        notifyListeners()
    }
}