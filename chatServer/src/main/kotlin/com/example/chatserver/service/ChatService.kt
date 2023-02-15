package com.example.chatserver.service

import com.example.chatserver.domain.entity.Chat
import com.example.chatserver.domain.repository.ChatRepository
import com.example.chatserver.model.ChatRequest
import com.example.chatserver.model.ChatResponse
import kotlinx.coroutines.flow.collect
import org.springframework.stereotype.Service

@Service
class ChatService(
    private val chatRepository: ChatRepository
) {
    suspend fun getChatsByRoomId(roomId: Long): List<ChatResponse> {
        val chatList = mutableListOf<ChatResponse>()
        chatRepository.findAllByRoomId(roomId = roomId)?.collect {
            chatList.add(ChatResponse(it))
        }
        return chatList
    }

    suspend fun saveChat(chatRequest: ChatRequest) {
        with(chatRequest) {
            val chat = Chat(
                roomId = roomId,
                username = username,
                contents = content
            )
            chatRepository.save(chat)
        }
    }
}