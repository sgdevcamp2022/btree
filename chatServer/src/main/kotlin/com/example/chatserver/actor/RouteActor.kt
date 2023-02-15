package com.example.chatserver.actor

import com.fasterxml.jackson.module.kotlin.jsonMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.kotlin.core.publisher.toMono

@OptIn(ObsoleteCoroutinesApi::class)
fun routeActor(session: WebSocketSession) = CoroutineScope(Dispatchers.Default).actor<UserOutgoingMessage> {
    val jsonMapper = jsonMapper()

    for (msg in channel) {
        session.send(
            session.textMessage(jsonMapper.writeValueAsString(msg)).toMono()
        ).awaitSingleOrNull()
    }
}