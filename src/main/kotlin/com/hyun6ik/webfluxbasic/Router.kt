package com.hyun6ik.webfluxbasic

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class Router {

    @Bean
    fun helloRouter(handler: HelloHandler) : RouterFunction<ServerResponse> {
        return route()
            .GET("/", handler::sayHello)
            .build()
    }
}