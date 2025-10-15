package org.example

import org.example.feature.game_of_life.presentation.controller.GameOfLifeController
import org.example.feature.game_of_life.presentation.model.GameOfLifeModel
import org.example.feature.game_of_life.presentation.view.GameOfLifeView
import org.example.presentation.common.main_frame.controller.MainController
import org.example.presentation.common.main_frame.model.NavigationModel
import org.example.presentation.common.main_frame.view.MainFrame
import javax.swing.SwingUtilities

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    SwingUtilities.invokeLater {
        //--- Build the main navigation model ---
        val navigationModel = NavigationModel()
        val mainFrame = MainFrame()
        val mainController = MainController(navigationModel, mainFrame)
        mainController.init()

        //--- Build the game of life model ---
        val gameOfLifeView = GameOfLifeView()
        val gameOfLifeModel = GameOfLifeModel()
        val gameOfLifeController = GameOfLifeController(gameOfLifeModel,gameOfLifeView)
        gameOfLifeController.init()

        //--- Connect the features to the main frame ---
        mainController.addFeaturePanel(gameOfLifeView,"Game of Life")

        mainFrame.isVisible = true
    }
}