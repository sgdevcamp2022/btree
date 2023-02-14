package com.example.chatserver.service

import com.example.chatserver.domain.repository.ChatRepository
import org.springframework.stereotype.Service

@Service
class ChatService(
    private val chatRepository: ChatRepository
) {

}