package org.example.presentation.common

import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import javax.swing.JPanel

/**
 * Un JPanel personnalisé qui se dessine avec des coins arrondis.
 * @param cornerRadius Le rayon des coins.
 */
open class RoundedPanel(private val cornerRadius: Int) : JPanel() {

    init {
        isOpaque = false
    }

    override fun paintComponent(graphics: Graphics) {
        super.paintComponent(graphics)

        // On utilise Graphics2D pour de meilleures options de dessin (comme l'anti-aliasing)
        val g2d = graphics as Graphics2D

        // Active l'anti-aliasing pour des bords lisses
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)

        // Définit la couleur de fond du panel
        g2d.color = background

        // Dessine un rectangle avec des coins arrondis qui remplit le panel
        // Le width et height sont les dimensions du panel
        // cornerRadius * 2 est utilisé pour les arcs de cercle des coins
        g2d.fillRoundRect(0, 0, width, height, cornerRadius * 2, cornerRadius * 2)
    }
}