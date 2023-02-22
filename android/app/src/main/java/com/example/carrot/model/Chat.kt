package com.example.carrot.model

import com.google.gson.annotations.SerializedName


data class ChatRoom(
    @SerializedName("roomId")
    val chattingRoomId: Long,
    val userName: String,
    val location: String,
    val updatedAt: String,
//    val userProfileImage: Int,
//    val postImage: Int,
//    val recentChat: String
)

data class Chat(
    val username: String,
//    val contentType: String,
    val content: String,
    val createdAt: String,
//    val readOrNot: Boolean
)
