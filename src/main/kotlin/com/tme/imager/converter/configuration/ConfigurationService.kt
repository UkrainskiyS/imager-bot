package com.tme.imager.converter.configuration

import com.tme.imager.converter.settings.Settings
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.awt.Font
import java.sql.ResultSet

@Component
class ConfigurationService(
  private val jdbcTemplate: JdbcTemplate,
) {

  fun changeEnabled(chatId: Long, enabled: Boolean) {
    jdbcTemplate.update("update settings set enabled = $enabled where chat_id = $chatId")
  }

  fun setSettings(chatId: Long, settings: Settings = Settings()) {
    jdbcTemplate.update(
      "update settings set font = ?, font_size = ?, background_color = ?, text_color = ?, text_mode = ? " +
        "where chat_id = $chatId",
      settings.font,
      settings.fontSize,
      settings.backgroundColor,
      settings.textColor,
      settings.textMode)
  }

  fun getConfiguration(chatId: Long): Configuration {
    return jdbcTemplate.queryForObject(
      "select font, font_size, background_color, text_color, text_mode from settings where chat_id = ?",
      { resultSet: ResultSet, _: Int ->
        Configuration(
          font = Font(resultSet.getString(1), Font.BOLD, resultSet.getInt(2)),
          backgroundColor = Configuration.parseColor(resultSet.getString(3)),
          textColor = Configuration.parseColor(resultSet.getString(4)),
          mode = TextMode.parse(resultSet.getString(5))
        )
      }, chatId) ?: Configuration()
  }

  fun isEnabled(chatId: Long): Boolean {
    return jdbcTemplate.query(
      "select enabled from settings where chat_id = $chatId limit 1"
    ) { it: ResultSet, _: Int ->
      it.getBoolean(1)
    }.let {
      it.isNotEmpty() && it.first()
    }
  }
}
