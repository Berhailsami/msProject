package org.example.feature.dog_flea.presentation.view

interface DogFleaControls {
    fun onDogFleasChanged(dogName:String, fleasNewNumber: Int)
    fun onStepClicked()
    fun onBackClicked()
    fun onResetClicked()
}