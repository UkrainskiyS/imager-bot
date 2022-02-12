package com.tme.imager.converter.configuration

import java.awt.Color
import java.awt.Font

class Configuration(
  val font: Font = Font("Arial", Font.BOLD, 150),
  val backgroundColor: Color = Color.BLACK,
  val textColor: Color = Color.WHITE,
  val mode: TextMode = TextMode.UPPER
) {

  companion object {
    fun parseColor(color: String): Color {
      return when (color) {
        "red" -> Color.RED
        "blue" -> Color.BLUE
        "white" -> Color.WHITE
        "yellow" -> Color.YELLOW
        "orange" -> Color.ORANGE
        "gray" -> Color.GRAY
        "green" -> Color.GREEN
        else -> Color.BLACK
      }
    }
  }
}
