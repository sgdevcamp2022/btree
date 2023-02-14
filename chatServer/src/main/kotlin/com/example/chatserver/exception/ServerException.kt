package com.example.chatserver.exception

sealed class ServerException(
    val code: Int,
    override val message: String,
): RuntimeException(message)

data class RoomNotFoundException(
    override val message: String = "존재하지 않는 채팅방입니다."
): ServerException(404, message)