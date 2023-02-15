package com.example.chatserver.controller

import com.example.chatserver.model.RoomRequest
import com.example.chatserver.model.RoomResponse
import com.example.chatserver.service.RoomService
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/chat/room")
class RoomController(
    private val roomService: RoomService
) {

    val log = KotlinLogging.logger {  }

    @PostMapping("/createRoom")
    @ResponseStatus(HttpStatus.OK)
    suspend fun createChatRoom(
        @RequestBody roomRequest: RoomRequest
    ){
        roomService.createChatRoom(roomRequest)
    }

    @GetMapping("/{email}")
    suspend fun getUserRooms(
        @PathVariable email: String
    ): List<RoomResponse> {
        return roomService.getRooms(email)
    }

    // TODO: delete room needed

}