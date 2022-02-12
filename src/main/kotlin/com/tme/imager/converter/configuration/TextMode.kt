package com.tme.imager.converter.configuration

enum class TextMode {
  UPPER {
    override fun modify(s: String) = s.uppercase()
  }, LOWER {
    override fun modify(s: String) = s.lowercase()
  }, DEFAULT {
    override fun modify(s: String) = s
  };

  abstract fun modify(s: String): String

  override fun toString(): String = super.toString().lowercase()

  companion object {
    fun parse(s: String): TextMode {
      return when (s) {
        "upper" -> UPPER
        "lower" -> LOWER
        else -> DEFAULT
      }
    }
  }
}
