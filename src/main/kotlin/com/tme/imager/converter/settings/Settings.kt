package com.tme.imager.converter.settings

import javax.persistence.*

@Entity
class Settings(

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private var id: Long? = null,

  @Column(name = "chat_id", nullable = false, unique = true)
  private var chatId: Long? = null,

  @Column(name = "font")
  var font: String = "Arial",

  @Column(name = "font_size")
  var fontSize: Int = 150,

  @Column(name = "background_color")
  var backgroundColor: String = "black",

  @Column(name = "text_color")
  var textColor: String = "white",

  @Column(name = "text_mode")
  var textMode: String = "upper",

  private var enabled: Boolean = true
) {

  override fun toString(): String {
    return "<b>Settings</b>\n\n" +
      "<b>Font</b> = $font\n" +
      "<b>Font size</b> = $fontSize\n" +
      "<b>Background color</b> = $backgroundColor\n" +
      "<b>Text color</b> = $textColor\n" +
      "<b>Mode</b> = $textMode\n\n" +
      "/setsettings $font $fontSize $backgroundColor $textColor $textMode"
  }
}
