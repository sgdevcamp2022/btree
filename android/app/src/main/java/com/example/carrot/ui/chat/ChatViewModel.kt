package com.example.carrot.ui.chat

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.carrot.api.RetrofitClient.authApiService
import com.example.carrot.api.RetrofitClient.chatApiService
import com.example.carrot.model.ChatRoom
import com.example.carrot.model.TokenStore

class ChatViewModel(

): ViewModel() {
    private val _chatRoomList = mutableStateListOf<ChatRoom>()
    val chatRoomList = _chatRoomList

    suspend fun setChatRoomList(context: Context){
        val tokenStore = TokenStore(context)
        tokenStore.getAccessToken.collect{
            val email = getEmail(token = it)
            Log.i("CHATROOM", "email : $email")
            try {
                val response = chatApiService.getChatRooms(email)
                when (response.code()) {
                    200 -> {
                        response.body()?.let { roomList ->
                            _chatRoomList.addAll(roomList)
                        }
                    }
                    else -> {
                        Log.i("CHATROOM", "chatroom request(${response.code()}) failed : ${response.body()}")
                    }
                }
            } catch (e: Exception) {
                Log.e("CHATROOM", "can't get chatroomlist : $e")
            }
        }
    }

    suspend fun getEmail(token: String): String{
        try {
            return authApiService.getMyInfo("Bearer $token").body()?.email!!
        } catch (e: Exception) {
            Log.e("CHATROOM", "can't get my info : $e")
            return ""
        }
    }
}