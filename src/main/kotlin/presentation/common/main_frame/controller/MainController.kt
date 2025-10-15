package org.example.presentation.common.main_frame.controller

import org.example.feature.game_of_life.presentation.view.GameOfLifeView
import org.example.presentation.common.main_frame.model.NavigationModel
import org.example.presentation.common.main_frame.view.MainFrame
import javax.swing.JPanel
class MainController(
        private val navigationModel: NavigationModel,
    private val mainFrame: MainFrame
) {
    fun init() {
        //--- Register the view to listen for model changes ---
        navigationModel.addListener(mainFrame)

        //--- Set up listeners for user actions (button clicks) ---
        mainFrame.sidePanelView.gameOfLifeButton.addActionListener {
            //--- When clicked, tell the MODEL to update ---
            navigationModel.setActiveScreen("GameOfLife")
        }

        //--- Set the initial state ---
        navigationModel.setActiveScreen("GameOfLife")
    }

    fun addFeaturePanel(panel: JPanel,name: String){
        mainFrame.mainContentView.addPanel(panel,name)
    }
}