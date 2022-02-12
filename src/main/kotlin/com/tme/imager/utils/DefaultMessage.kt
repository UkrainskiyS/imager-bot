package com.tme.imager.utils

import org.telegram.telegrambots.meta.api.methods.send.SendMessage

object DefaultMessage {

  private const val LONG_TEXT = "Можно что нибудь покороче?"
  private const val IN_PROCESS = "Колдую... Не пиши ничего!"

  fun executeMessage(command: String, chatId: Long) = SendMessage(chatId.toString(), "$command ok")

  fun veryLongText(chatId: Long) = SendMessage(chatId.toString(), LONG_TEXT)

  fun inProcess(chatId: Long) = SendMessage(chatId.toString(), IN_PROCESS)
}
