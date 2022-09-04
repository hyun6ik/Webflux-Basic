package com.hyun6ik.webfluxbasic

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class UserHandler {

    val users = listOf(
        User(id = 1, email = "user1@gmail.com"),
        User(id = 2, email = "user2@gmail.com")
    )

    fun getUser(request: ServerRequest): Mono<ServerResponse> {
        return users.find {
            user -> request.pathVariable("id").toLong() == user.id }
            ?.let {
              user ->  ServerResponse.ok().bodyValue(user)
            } ?: ServerResponse.notFound().build()
    }

    fun getAll(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().bodyValue(users)
    }

}