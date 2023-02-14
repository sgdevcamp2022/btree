package com.example.chatserver.controller

import com.example.chatserver.model.ChatRequest
import com.example.chatserver.model.ChatResponse
import com.example.chatserver.service.ChatService
import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController("/chat")
class ChatController(
    private val chatService: ChatService
) {

}