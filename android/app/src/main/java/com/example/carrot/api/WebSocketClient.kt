package com.example.carrot.api

import okhttp3.*
import okio.ByteString

// chat room 으로 옮길 예정
object WebSocketClient {

    val okHttpClient = OkHttpClient()
    val request = Request.Builder()
        .url("ws://39.121.157.64:10000/chat/chatText")
        .build()
    val webSocket = okHttpClient.newWebSocket(
        request = request,
        object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosed(webSocket, code, reason)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
            }
        }
    )
}