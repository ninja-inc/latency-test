package com.ninja.reactiveapp

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Mono
import java.util.*

@Configuration
class ReactiveAppRouter {
    private val log = LoggerFactory.getLogger(this::class.java)
    private val timer = Timer()

    @Bean
    fun routerFunction(): RouterFunction<ServerResponse> = router {
        GET("/slow") { req ->
            log.info("/slow")
            get(req)
        }
        GET("/normal") { _ ->
            log.info("/normal")

            ServerResponse.ok().body(Mono.create { e ->
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        e.success(Response("OK!"))
                    }
                }, 200)
            })
        }
    }

    fun get(request: ServerRequest): Mono<ServerResponse> = ServerResponse.ok().body(
            WebClient.create("http://localhost:8081/slow")
                    .get()
                    .retrieve()
                    .bodyToMono(Response::class.java)
    )
}

data class Response(
        val msg: String
)
