package com.ninja.slowdownapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SlowDownApp

fun main(args: Array<String>) {
    runApplication<SlowDownApp>(*args)
}
