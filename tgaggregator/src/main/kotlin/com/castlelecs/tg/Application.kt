package com.castlelecs.tg

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = ["com.castlelecs"]
)
open class Application

fun main(args: Array<String>) {
    System.loadLibrary("tdjni")
    runApplication<Application>(*args)
}
