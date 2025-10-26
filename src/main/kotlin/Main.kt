package org.example

import org.example.core.domain.use_case.DogFleaUseCase
import org.example.feature.dog_flea.presentation.controller.DogFleaController
import org.example.feature.dog_flea.presentation.model.DogFleaModel
import org.example.feature.dog_flea.presentation.view.DogFleaView
import org.example.feature.game_of_life.presentation.controller.GameOfLifeController
import org.example.feature.game_of_life.presentation.model.GameOfLifeModel
import org.example.feature.game_of_life.presentation.view.GameOfLifeView
import org.example.presentation.common.main_frame.controller.MainController
import org.example.presentation.common.main_frame.model.NavigationModel
import org.example.presentation.common.main_frame.view.MainFrame
import javax.swing.SwingUtilities

fun main() {
    SwingUtilities.invokeLater {
        //--- Main navigation ---
        val navigationModel = NavigationModel()
        val mainFrame = MainFrame()
        val mainController = MainController(navigationModel, mainFrame)
        mainController.init()

        //--- Game of Life ---
        val gameOfLifeModel = GameOfLifeModel()
        val gameOfLifeView = GameOfLifeView()
        val gameOfLifeController = GameOfLifeController(
            model = gameOfLifeModel,
            view = gameOfLifeView
        )

        //--- Dog Flea ---
        val dogFleaModel = DogFleaModel()
        val dogFleaView = DogFleaView()
        val dogFleaController = DogFleaController(
            model = dogFleaModel,
            view = dogFleaView,
        )

        //--- Connect features to the main frame ---
        mainController.addFeaturePanel(gameOfLifeView, "GameOfLife")
        mainController.addFeaturePanel(dogFleaView, "DogFlea")

        mainFrame.isVisible = true
    }
}
