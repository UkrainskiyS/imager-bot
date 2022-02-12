package com.tme.imager.service.commands

object Regexp {
  val SET_SETTINGS: Regex = Regex("/setsettings@?.*")
  val SET_DEFAULT_SETTINGS: Regex = Regex("/setdefaultsettings@?.*")
  val GET_SETTINGS: Regex = Regex("/getsettings@?.*")
  val STOP: Regex = Regex("/stop@?.*")
  val START: Regex = Regex("/start@?.*")
  val HELP: Regex = Regex("/help@?.*")
}
