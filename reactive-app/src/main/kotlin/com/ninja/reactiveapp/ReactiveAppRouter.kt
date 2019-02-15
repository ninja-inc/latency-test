package com.ninja.reactiveapp

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Mono

@Configuration
class ReactiveAppRouter {
    private val log = LoggerFactory.getLogger(this::class.java)

    @Bean
    fun routerFunction(): RouterFunction<ServerResponse> = router {
        GET("/") { req ->
            get(req)
        }
    }

    fun get(request: ServerRequest): Mono<ServerResponse> = ServerResponse.ok().body(
            WebClient.create("http://localhost:8081/slowDownRandomly")
                    .get()
                    .retrieve()
                    .bodyToMono(Response::class.java)
    )
}

data class Response(
        val msg: String
)
