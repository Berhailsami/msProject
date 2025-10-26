package org.example.feature.dog_flea.presentation.view

interface DogFleaControls {
    fun onFleasDogAChanged(fleasDogA: Int)
    fun onFleasDogBChanged(fleasDogB: Int)
    fun onStepClicked()
    fun onBackClicked()
    fun onResetClicked()
}