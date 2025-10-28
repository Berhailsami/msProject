package org.example.feature.dog_flea.presentation.view.component

import org.example.core.domain.model.dog_flea.Dog
import org.example.core.domain.model.dog_flea.Generation
import org.example.feature.dog_flea.presentation.model.DogFleaListener
import org.example.presentation.common.RoundedPanel
import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Image
import java.awt.RenderingHints
import javax.swing.ImageIcon
import javax.swing.SwingUtilities

class DogView (
    imagePath: String,
    dogName: String,
) : RoundedPanel(15) , DogFleaListener {


    private var dogImage : ImageIcon? = null
    private var dog : Dog = Dog(dogName,0)
    private val errorMessage = "Error loading image"
    private val scaledWidth = 450


    init {
        background = Color.WHITE
        try {
            val imageUrl = javaClass.getResource("/$imagePath")
            if (imageUrl != null) {
                val originalIcon = ImageIcon(imageUrl)
                this.dogImage = scaleImage(originalIcon, scaledWidth)
                preferredSize = Dimension(dogImage!!.iconWidth, dogImage!!.iconHeight)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun scaleImage(icon: ImageIcon, newWidth: Int): ImageIcon {
        val image = icon.image

        // Calculate the new height to maintain the aspect ratio
        val originalWidth = image.getWidth(null)
        val originalHeight = image.getHeight(null)
        val newHeight = (originalHeight.toDouble() / originalWidth.toDouble() * newWidth).toInt()

        // Create a new scaled image
        val scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH)

        return ImageIcon(scaledImage)
    }

    override fun onGenerationCalculated(generation: Generation) {
        SwingUtilities.invokeLater {
            generation.dogs.forEach { dog->
                if (dog.name == this.dog.name){
                    this.dog = dog
                    this.revalidate()
                    this.repaint()
                }
            }
        }
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        val g2d = g as Graphics2D

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON)

        if (dogImage != null) {
            // Draw the dog image centered in the panel
            val x_img = (width - dogImage!!.iconWidth) / 2
            val y_img = (height - dogImage!!.iconHeight) / 2
            dogImage!!.paintIcon(this, g2d, x_img, y_img)

            // --- Draw the number ---
            val fleaFont = Font("Arial", Font.BOLD, 64)
            g2d.font = fleaFont
            g2d.color = Color.RED

            val text = dog.fleasNumber.toString()
            val fm = g2d.fontMetrics
            val textWidth = fm.stringWidth(text)
            val textAscent = fm.ascent
            val textDescent = fm.descent

            // Calculate centered coordinates
            val x_text = (width - textWidth) / 2
            val y_text = (height - (textAscent + textDescent)) / 2 + textAscent

            g2d.drawString(text, x_text, y_text)

        } else {
            // Fallback if image failed to load
            g2d.color = Color.LIGHT_GRAY
            g2d.fillRect(0, 0, width, height)
            g2d.color = Color.BLACK
            g2d.drawString(errorMessage, 10, 20)
        }
    }
}