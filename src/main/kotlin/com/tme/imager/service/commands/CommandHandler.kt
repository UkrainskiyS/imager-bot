package com.tme.imager.service.commands

import com.tme.imager.converter.configuration.ConfigurationService
import com.tme.imager.converter.settings.Settings
import com.tme.imager.converter.settings.SettingsRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.ParseMode
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import java.nio.file.Files
import java.nio.file.Path

@Component
class CommandHandler(
  val settingsRepository: SettingsRepository,
  val configurationService: ConfigurationService,
) : Commands {

  private val logger = LoggerFactory.getLogger(this.javaClass)

  override fun start(chatId: Long) {
    if (settingsRepository.existsByChatId(chatId)) {
      configurationService.changeEnabled(chatId, true)
    } else {
      settingsRepository.save(Settings(chatId = chatId))
    }
    logger.info("Bot started, chatId=$chatId")
  }

  override fun stop(chatId: Long) {
    configurationService.changeEnabled(chatId, false)
    logger.info("Bot stopped, chatId=$chatId")
  }

  override fun setDefaultSettings(chatId: Long) {
    configurationService.setSettings(chatId)
    logger.info("Set default settings, chatId=$chatId")
  }

  override fun getSettings(chatId: Long): SendMessage {
    return settingsRepository.findByChatId(chatId)!!.let {
      SendMessage.builder()
        .chatId(chatId.toString())
        .text(it.toString())
        .parseMode(ParseMode.HTML)
        .build()
    }
  }

  override fun setSettings(text: String, chatId: Long) {
    text.split(' ').let {
      configurationService.setSettings(chatId,
        Settings(
          font = it[1],
          fontSize = it[2].toInt(),
          backgroundColor = it[3],
          textColor = it[4],
          textMode = it[5]
        )
      )
    }

    logger.info("Update settings, chatId=$chatId")
  }

  override fun help(chatId: Long): SendMessage {
    return SendMessage(
      chatId.toString(),
      String(Files.readAllBytes(Path.of("src/main/resources/help.txt")))
    )
  }
}
