package com.tme.imager.config

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@Configuration
class Cleaner {

  private var logger = LoggerFactory.getLogger(this.javaClass)
  private val path = "images/"

  init {
    if (!Files.exists(Path.of(path))) {
      Files.createDirectory(Path.of(path))
      logger.info("Directory $path created", )
    }

    val ex = Executors.newSingleThreadScheduledExecutor()
    logger.info("Cleaner initialized, path=$path")

    ex.scheduleAtFixedRate(
      object : TimerTask() {
        override fun run() {
          Files.walk(Path.of(path))
            .filter(Files::isRegularFile)
            .forEach { Files.delete(it) }
          logger.info("Directory $path cleaned")
        }
      }, 0, 10, TimeUnit.MINUTES
    )
  }
}
