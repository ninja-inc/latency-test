package com.ninja.servletapp

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient

@RestController
class ServletAppController {
    @GetMapping("/")
    fun get(): Response = WebClient.create("http://localhost:8081/slowDownRandomly")
            .get()
            .retrieve()
            .bodyToMono(Response::class.java)
            .block()!!
}

data class Response(
        val msg: String
)
