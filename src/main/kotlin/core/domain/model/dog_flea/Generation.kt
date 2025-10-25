package org.example.core.domain.model.dog_flea

data class Generation(
    val genNumber: Int,
    val randomNumber: Double,
    val totalFleas: Int,
    val dogs: List<Dog>
)
