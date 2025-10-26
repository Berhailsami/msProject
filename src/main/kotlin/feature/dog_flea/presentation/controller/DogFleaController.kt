package org.example.feature.dog_flea.presentation.controller

import org.example.feature.dog_flea.presentation.model.DogFleaModel
import org.example.feature.dog_flea.presentation.view.DogFleaControls
import org.example.feature.dog_flea.presentation.view.DogFleaView

class DogFleaController (
    private val model: DogFleaModel,
    private val view: DogFleaView,
) : DogFleaControls{

    fun init(){
        view.setControls(this)
        model.addListener(view.dogA)
    }

    override fun onFleasDogAChanged(fleasDogA: Int) {
        TODO("Not yet implemented")
    }

    override fun onFleasDogBChanged(fleasDogB: Int) {
        TODO("Not yet implemented")
    }

    override fun onStepClicked() {
        TODO("Not yet implemented")
    }

    override fun onBackClicked() {
        TODO("Not yet implemented")
    }

    override fun onResetClicked() {
        TODO("Not yet implemented")
    }

}