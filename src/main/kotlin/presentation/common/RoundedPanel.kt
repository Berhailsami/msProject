package org.example.presentation.common

import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import javax.swing.JPanel

/**
 * A custom JPanel that draws itself with rounded corners.
 * @param cornerRadius The radius of the corners.
 */
open class RoundedPanel(private val cornerRadius: Int) : JPanel() {

    init {
        isOpaque = false
    }

    override fun paintComponent(graphics: Graphics) {
        super.paintComponent(graphics)

        // We use Graphics2D for better drawing options (like anti-aliasing).
        val g2d = graphics as Graphics2D

        // Enable anti-aliasing for smooth edges.
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        )

        g2d.color = background

        // Draw a rectangle with rounded corners that fills the panel.
        // The width and height are the dimensions of the panel.
        // cornerRadius * 2 is used for the corner arcs.
        g2d.fillRoundRect(
            0,
            0,
            width,
            height,
            cornerRadius * 2,
            cornerRadius * 2
        )
    }
}