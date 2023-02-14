package com.example.chatserver.service

import com.example.chatserver.domain.entity.Room
import com.example.chatserver.domain.repository.RoomRepository
import com.example.chatserver.model.RoomRequest
import com.example.chatserver.model.RoomResponse
import kotlinx.coroutines.flow.collect
import org.springframework.stereotype.Service

@Service
class RoomService(
    private val roomRepository: RoomRepository
) {
    suspend fun createChatRoom(roomRequest: RoomRequest) {
        with(roomRequest){
            val room = Room(
                buyerEmail = buyerEmail,
                buyerName = buyerName,
                sellerEmail = sellerEmail,
                sellerName = sellerName,
                location = location
            )
            roomRepository.save(room)
        }
    }

    suspend fun getRooms(userEmail: String): List<RoomResponse>{
        val roomList = mutableListOf<RoomResponse>()
        roomRepository.findAllByBuyerEmailOrSellerEmail(userEmail, userEmail)?.collect{
            roomList.add(RoomResponse(it, userEmail))
        }
        return roomList
    }

}