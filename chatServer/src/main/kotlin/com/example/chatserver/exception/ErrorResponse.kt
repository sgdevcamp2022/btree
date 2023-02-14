package com.example.chatserver.exception

data class ErrorResponse(
    val code: Int,
    val message: String
)