package org.example.presentation.common.main_frame.model

class NavigationModel {
    private val listeners = mutableListOf<NavigationListener>()

    fun addListener(listener: NavigationListener) {
        listeners.add(listener)
    }

    fun setActiveScreen(screenName: String) {
        listeners.forEach { it.onScreenChanged(screenName) }
    }
}