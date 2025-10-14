package org.example.presentation.common

import org.example.feature.game_of_life.presentation.view.GameOfLifeView
import org.example.presentation.common.main_frame.model.NavigationModel
import org.example.presentation.common.main_frame.view.MainFrame
import javax.swing.JPanel // For a placeholder panel

class MainController(
        private val navigationModel: NavigationModel,
    private val mainFrame: MainFrame
) {
    fun init() {
        // 1. Register the view to listen for model changes
        navigationModel.addListener(mainFrame)

        // 2. Add screens to the main content view
        mainFrame.mainContentView.addPanel(GameOfLifeView(), "GameOfLife")
        mainFrame.mainContentView.addPanel(JPanel(), "AnotherScreen") // Placeholder

        // 3. Set up listeners for user actions (button clicks)
        mainFrame.sidePanelView.gameOfLifeButton.addActionListener {
            // When clicked, tell the MODEL to update
            navigationModel.setActiveScreen("GameOfLife")
        }

        mainFrame.sidePanelView.anotherScreenButton.addActionListener {
            // When clicked, tell the MODEL to update
            navigationModel.setActiveScreen("AnotherScreen")
        }

        // 4. Set the initial state
        navigationModel.setActiveScreen("GameOfLife")
    }
}