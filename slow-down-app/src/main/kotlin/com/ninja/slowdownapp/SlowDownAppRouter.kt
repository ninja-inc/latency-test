package com.ninja.slowdownapp

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Mono
import java.util.concurrent.TimeUnit

@Configuration
class SlowDownAppRouter {
    private val log = LoggerFactory.getLogger(this::class.java)

    // https://objectpartners.com/2017/11/16/spring-webflux-functional-endpoints/
    @Bean
    fun routerFunction(): RouterFunction<ServerResponse> = router {
        GET("/slowDownRandomly") { req ->
            slowDownRandomly(req)
        }
    }

    fun slowDownRandomly(request: ServerRequest): Mono<ServerResponse> {
        TimeUnit.SECONDS.sleep(30)

        return ServerResponse.ok().body(Mono.just(SlowDownResponse("slow down")))
    }
}

data class SlowDownResponse(
        val msg: String
)
