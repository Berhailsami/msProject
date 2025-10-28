package org.example.core.domain.use_case

import org.example.core.domain.model.dog_flea.Generation

class DogFleaUseCase {

    fun invoke(currentGeneration: Generation) : Generation {

        val randomNumber = currentGeneration.randomNumber
        val totalFleas = currentGeneration.totalFleas
        val dogs = currentGeneration.dogs

        val dogA = dogs[0]
        val dogB = dogs[1]

        val probA = dogA.fleasNumber.toDouble() / totalFleas.toDouble()

        val (newDogA, newDogB) = if (randomNumber < probA) {
            val newA = dogA.copy(fleasNumber = dogA.fleasNumber - 1)
            val newB = dogB.copy(fleasNumber = dogB.fleasNumber + 1)
            newA to newB
        } else {
            val newA = dogA.copy(fleasNumber = dogA.fleasNumber + 1)
            val newB = dogB.copy(fleasNumber = dogB.fleasNumber - 1)
            newA to newB
        }

        return Generation(
            dogs = listOf(newDogA, newDogB),
            totalFleas = newDogA.fleasNumber + newDogB.fleasNumber,
            randomNumber = randomNumber,
            genNumber = currentGeneration.genNumber + 1
        )

    }

}