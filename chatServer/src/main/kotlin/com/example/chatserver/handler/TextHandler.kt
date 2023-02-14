package com.example.chatserver.handler

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.reactor.mono
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Mono

@InternalCoroutinesApi
class TextHandler: WebSocketHandler {
    override fun handle(session: WebSocketSession): Mono<Void> =
        mono {
            handleSuspend(session)
        }
            .then()

    private suspend fun handleSuspend(session: WebSocketSession){

    }
}