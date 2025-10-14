package org.example

import org.example.presentation.common.main_frame.controller.MainController
import org.example.presentation.common.main_frame.model.NavigationModel
import org.example.presentation.common.main_frame.view.MainFrame
import javax.swing.SwingUtilities

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    SwingUtilities.invokeLater {

        val model = NavigationModel()
        val view = MainFrame()
        val controller = MainController(model, view)

        controller.init()

        view.isVisible = true
    }
}