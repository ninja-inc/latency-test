package com.ninja.servletapp

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient

@RestController
class ServletAppController {
    private val log = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/slow")
    fun slow(): Response {
        log.info("/slow")

        return WebClient.create("http://localhost:8081/slow")
                .get()
                .retrieve()
                .bodyToMono(Response::class.java)
                .block()!!
    }

    @GetMapping("/normal")
    fun normal(): Response {
        log.info("/normal")

        Thread.sleep(200)

        return Response("ok!")
    }
}

data class Response(
        val msg: String
)
