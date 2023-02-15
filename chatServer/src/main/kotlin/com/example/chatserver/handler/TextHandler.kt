package com.example.chatserver.handler

import com.example.chatserver.actor.*
import com.example.chatserver.model.Rooms
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.mono
import mu.KotlinLogging
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
        val logger = KotlinLogging.logger {  }
        logger.info { session.handshakeInfo.uri }
        val params = parseQueryString(session.handshakeInfo.uri)
        val roomId = params["roomId"]!!.toInt()
        val username = params["username"] ?: "anonymous"

        val roomActor = Rooms.findOrCreate(roomId)
        val userActor = userActor(roomActor)

        val routeActor = routeActor(session)

        val connectedMsg = Connected(
            routeActor = routeActor,
            username = username
        )

        userActor.send(connectedMsg)

        session.receive()
            .log()
            .map { it.retain() }
            .asFlow()
            .onCompletion { userActor.send(Completed) }
            .collect {
                val userIncomingMessage = UserIncomingMessage(username, it.payloadAsText)

                userActor.send(userIncomingMessage)
            }
    }
}