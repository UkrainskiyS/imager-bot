package com.tme.imager.converter.settings

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SettingsRepository : JpaRepository<Settings, Long> {

  fun findByChatId(chatId: Long): Settings?

  fun existsByChatId(chatId: Long): Boolean
}
