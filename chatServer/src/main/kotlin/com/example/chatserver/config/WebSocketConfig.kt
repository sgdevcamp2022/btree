package com.example.chatserver.config

import com.example.chatserver.handler.ImageHandler
import com.example.chatserver.handler.LocationHandler
import com.example.chatserver.handler.TextHandler
import kotlinx.coroutines.InternalCoroutinesApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter

@Configuration
class WebSocketConfig {
    @Bean
    @InternalCoroutinesApi
    fun handleMapping(): HandlerMapping {
        val map = mapOf(
            "/chatText" to TextHandler(),
            "/chatImage" to ImageHandler(),
            "/chatLocation" to LocationHandler()
        )

        return SimpleUrlHandlerMapping(map, -1)
    }
    @Bean
    fun handlerAdapter() = WebSocketHandlerAdapter()
}