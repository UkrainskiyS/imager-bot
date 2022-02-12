package com.tme.imager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ImagerApplication

fun main(args: Array<String>) {
  runApplication<ImagerApplication>(*args)
}
