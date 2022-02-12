package com.tme.imager.telegram

import com.tme.imager.config.BotConfig
import com.tme.imager.converter.configuration.ConfigurationService
import com.tme.imager.service.ImagerService
import com.tme.imager.utils.DefaultMessage
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class ImagerBot(
  private val botConfig: BotConfig,
  private val imagerService: ImagerService,
  private val configurationService: ConfigurationService
) : TelegramLongPollingBot() {

  private val logger = LoggerFactory.getLogger(this.javaClass)

  override fun onUpdateReceived(update: Update?) {
    if (update?.hasMessage() == true && update.message?.hasText() == true) {
      if (update.message.text.length > 50) {
        execute(DefaultMessage.veryLongText(update.message.chatId))
        logger.info("Incorrect length text chatId=${update.message.chatId}")
        return
      }

      if (botConfig.isCommand(update.message.text)) {
        execute(imagerService.executeCommand(update.message.text, update.message.chatId))

      } else if (configurationService.isEnabled(update.message.chatId)) {
        execute(DefaultMessage.inProcess(update.message.chatId))
        execute(imagerService.getImage(update.message.text, update.message.chatId))

        logger.info("Image sent chatId=${update.message.chatId}, text=${update.message.text}")
      }
    }
  }

  override fun getBotToken() = botConfig.token

  override fun getBotUsername() = botConfig.name
}
