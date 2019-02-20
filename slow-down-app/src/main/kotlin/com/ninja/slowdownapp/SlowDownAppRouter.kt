package com.ninja.slowdownapp

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Mono
import java.util.*

@Configuration
class SlowDownAppRouter {
    private val log = LoggerFactory.getLogger(this::class.java)
    private val timer = Timer()

    // https://objectpartners.com/2017/11/16/spring-webflux-functional-endpoints/
    @Bean
    fun routerFunction(): RouterFunction<ServerResponse> = router {
        GET("/slow") { req ->
            slowDownRandomly(req)
        }
    }

    fun slowDownRandomly(request: ServerRequest): Mono<ServerResponse> = ServerResponse.ok().body(
            Mono.create { e ->
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        e.success(SlowDownResponse("slow down!"))
                    }
                }, 30_000)
            })
}

data class SlowDownResponse(
        val msg: String
)
