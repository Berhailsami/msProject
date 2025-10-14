package org.example.feature.game_of_life.presentation.view

import org.example.feature.game_of_life.presentation.model.GameOfLifeListener
import org.example.presentation.common.RoundedPanel
import java.awt.Color
import javax.swing.JLabel

class GameOfLifeView : RoundedPanel(15) , GameOfLifeListener{
    init {
        this.add(JLabel("Game of Life"))
        this.background = Color.WHITE
    }
}