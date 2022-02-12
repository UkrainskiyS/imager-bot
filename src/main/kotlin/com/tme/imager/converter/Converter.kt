package com.tme.imager.converter

import com.tme.imager.converter.configuration.Configuration
import java.awt.RenderingHints
import java.awt.font.FontRenderContext
import java.awt.geom.AffineTransform
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class Converter(
  private var text: String = "",
  private val configuration: Configuration = Configuration()
) {

  private var width: Int = 0
  private var height: Int = 0
  private var x: Int = 0
  private var y: Int = 0

  companion object {
    const val PATH_TO_IMAGE = "images/%s.jpg"
    val RENDER_CONTEXT = FontRenderContext(AffineTransform(), true, true)
  }

  init {
    text = configuration.mode.modify(text)

    val textLength = configuration.font.getStringBounds(text, RENDER_CONTEXT).width.toInt()
    height = ((configuration.font.size * 2.3).toInt())
    x = (textLength * 0.2).toInt()
    y = ((configuration.font.size * 1.45).toInt())
    width = x * 2 + textLength

    println(height + width + x + y)
  }

  fun getImage(): String {
    val bufferedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    val graphics2D = bufferedImage.createGraphics()
    graphics2D.setRenderingHint(
      RenderingHints.KEY_TEXT_ANTIALIASING,
      RenderingHints.VALUE_TEXT_ANTIALIAS_ON
    )

    graphics2D.color = configuration.backgroundColor
    graphics2D.fillRect(0, 0, width, height)

    graphics2D.color = configuration.textColor
    graphics2D.font = configuration.font
    graphics2D.drawString(text, x, y)

    return saveImage(bufferedImage)
  }

  private fun saveImage(bufferedImage: BufferedImage): String {
    val path = String.format(PATH_TO_IMAGE, (Math.random() * 10000).toInt())
    ImageIO.write(bufferedImage, "jpg", File(path))
    return path
  }
}
