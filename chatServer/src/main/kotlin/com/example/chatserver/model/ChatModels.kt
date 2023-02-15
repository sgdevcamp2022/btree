package com.example.chatserver.model

import com.example.chatserver.domain.entity.Chat

data class ChatResponse(
    val username: String,
    val content: String,
    val createdAt: String
) {
    companion object {
        operator fun invoke(chat: Chat) = with(chat) {
            ChatResponse(
                username = username,
                content = contents,
                createdAt = createdAt.toString()
            )
        }
    }
}

data class ChatRequest(
    val roomId: Long,
    val username: String,
    val content: String
)

