package com.example.carrot.api

import com.example.carrot.model.ChatRoom
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChatApi {

    @GET("/chat/room/{email}")
    suspend fun getChatRooms(@Path("email") email: String): Response<List<ChatRoom>>

    @POST("/chat/createRoom")
    suspend fun createChatRoom()

}