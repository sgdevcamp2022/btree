package com.example.chatserver.domain.repository

import com.example.chatserver.domain.entity.Room
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface RoomRepository: CoroutineCrudRepository<Room, Long> {
    suspend fun findAllByBuyerEmailOrSellerEmail(buyerEmail: String, sellerEmail: String): Flow<Room>?
}