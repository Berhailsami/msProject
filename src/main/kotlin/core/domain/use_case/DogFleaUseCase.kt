package org.example.core.domain.use_case

import org.example.core.domain.model.dog_flea.Generation

class DogFleaUseCase {

    fun invoke(currentGeneration: Generation) {
        val randomNumber = Math.random()
        val totalFleas = currentGeneration.totalFleas
        val dogs = currentGeneration.dogs

    }

}