package org.example.presentation.common.main_frame.view

import org.example.presentation.common.main_frame.model.NavigationListener
import org.example.presentation.common.main_panel.view.MainContentView
import org.example.presentation.common.side_panel.view.SidePanelView
import java.awt.BorderLayout
import java.awt.Color
import javax.swing.BorderFactory
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.SwingUtilities

class MainFrame : JFrame(), NavigationListener {

    val sidePanelView = SidePanelView()
    val mainContentView = MainContentView()

    init {

        title = "msProject"
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(
            1400,
            800
        )
        minimumSize = size
        setLocationRelativeTo(null)
        layout = BorderLayout(
            10,
            10
        )
        isResizable = true
        contentPane.background = Color.decode("#D8E5F2")
        (contentPane as JPanel).border = BorderFactory
            .createEmptyBorder(
                10,
                10,
                10,
                10
            )

        add(sidePanelView, BorderLayout.WEST)
        add(mainContentView, BorderLayout.CENTER)
    }

    //--- This method is called by the Model when the active screen changes ---
    override fun onScreenChanged(screenName: String) {
        SwingUtilities.invokeLater {
            mainContentView.showPanel(screenName)
        }
    }
}