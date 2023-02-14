package com.example.chatserver.domain.enums

enum class ContentsType {

    TEXT, IMAGE, LOCATION, CALL;

    companion object {
        operator fun invoke(type: String) = valueOf(type.uppercase())
    }
}