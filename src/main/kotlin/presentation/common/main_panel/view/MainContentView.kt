package org.example.presentation.common.main_panel.view

import org.example.presentation.common.RoundedPanel
import java.awt.CardLayout
import java.awt.Color
import javax.swing.JPanel

class MainContentView : RoundedPanel(15) {
    private val cardLayout = CardLayout()
    init {
        background = Color.WHITE
        layout = cardLayout
    }
    fun addPanel(panel: JPanel,name: String){
        this.add(panel,name)
    }
    fun showPanel(name: String){
        cardLayout.show(this,name)
    }
}