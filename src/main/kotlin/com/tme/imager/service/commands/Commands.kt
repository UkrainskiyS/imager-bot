package com.tme.imager.service.commands

import org.telegram.telegrambots.meta.api.methods.send.SendMessage

interface Commands {

  fun start(chatId: Long)

  fun stop(chatId: Long)

  fun getSettings(chatId: Long): SendMessage

  fun setDefaultSettings(chatId: Long)

  fun setSettings(text: String, chatId: Long)

  fun help(chatId: Long): SendMessage
}
