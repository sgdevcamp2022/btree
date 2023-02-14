package com.example.chatserver.model

import com.example.chatserver.domain.entity.Room


data class RoomRequest(
    val buyerEmail: String,
    val buyerName: String,
    val sellerEmail: String,
    val sellerName: String,
    val location: String
)
data class RoomResponse(
    val roomId: Long,
    val userName: String,
    val location: String,
    val updatedAt: String,
) {
    companion object {
        operator fun invoke(room: Room, email: String) = with(room){
            RoomResponse(
                roomId = roomId!!,
                userName = if (email == buyerEmail) sellerName else buyerName,
                location = location,
                updatedAt = updatedAt.toString()
            )
        }
    }
}