package org.example.feature.dog_flea.presentation.model

import org.example.core.domain.model.dog_flea.Generation

interface DogFleaListener {
    fun onGenerationCalculated(generation: Generation)
}