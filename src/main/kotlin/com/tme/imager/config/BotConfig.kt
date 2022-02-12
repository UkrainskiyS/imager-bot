package com.tme.imager.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
@ConfigurationProperties("bot")
class BotConfig {

  lateinit var name: String
  lateinit var token: String
  lateinit var commands: MutableList<String>

  @PostConstruct
  private fun initCommands() = commands.addAll(commands.map { "$it@$name" })

  fun isCommand(text: String) = commands.contains(text.split(' ')[0])
}
