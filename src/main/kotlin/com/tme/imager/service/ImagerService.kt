package com.tme.imager.service

import com.tme.imager.converter.Converter
import com.tme.imager.converter.configuration.ConfigurationService
import com.tme.imager.service.commands.Commands
import com.tme.imager.service.commands.Regexp
import com.tme.imager.utils.DefaultMessage
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto
import org.telegram.telegrambots.meta.api.objects.InputFile
import java.io.File

@Service
class ImagerService(
  val commands: Commands,
  val configurationService: ConfigurationService,
) {

  fun getImage(text: String, chatId: Long): SendPhoto {
    val converter = Converter(text, configurationService.getConfiguration(chatId))

    return SendPhoto(
      chatId.toString(),
      InputFile(File(converter.getImage()))
    )
  }

  fun executeCommand(command: String, chatId: Long): SendMessage {
    when {
      command.matches(Regexp.SET_SETTINGS) -> {
        commands.setSettings(command, chatId)
        return DefaultMessage.executeMessage(command.split(" ")[0], chatId)
      }
      command.matches(Regexp.GET_SETTINGS) -> return commands.getSettings(chatId)
      command.matches(Regexp.HELP) -> return commands.help(chatId)

      command.matches(Regexp.SET_DEFAULT_SETTINGS) -> commands.setDefaultSettings(chatId)
      command.matches(Regexp.START) -> commands.start(chatId)
      command.matches(Regexp.STOP) -> commands.stop(chatId)
    }

    return DefaultMessage.executeMessage(command, chatId)
  }
}
