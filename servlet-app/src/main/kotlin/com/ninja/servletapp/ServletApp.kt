package com.ninja.servletapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ServletApp

fun main(args: Array<String>) {
    runApplication<ServletApp>(*args)
}
