package com.example.chatserver.domain.repository

import com.example.chatserver.domain.entity.Chat
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface ChatRepository: CoroutineCrudRepository<Chat, Long> {

    suspend fun findAllByRoomId(roomId: Long): Flow<Chat>?
}