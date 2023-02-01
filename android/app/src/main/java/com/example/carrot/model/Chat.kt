package com.example.carrot.model

data class ChattingRoom(
    val chattingRoomId: Long,
    val user: User = SampleData.sampleUser[1],
    val post: SalePost = SampleData.sampleSalePost[1],
    val updatedAt: Int = 10,
    val recentChat: String = "안녕하세요?",
    val chatList: List<Chat> = SampleData.sampleChat
)

data class Chat(
    val chatId: Long,
    val meOrNot: Boolean,
    val user: User = SampleData.sampleUser[1],
    val contentType: String = "텍스트",
    val content: String = "안녕하세요?",
    val createdAt: Int = 10,
    val readOrNot: Boolean
)

//data class ChattingRoom(
//    val chattingRoomId: Long,
//    val user: User,
//    val post: SalePost,
//    val updatedAt: Int,
//    val recentChat: String
//)
//
//data class Chat(
//    val meOrNot: Boolean,
//    val user: User,
    // TODO: enum class로 콘텐츠 타입 정의할 것
//    val contentType: String,
//    val content: String,
    // TODO: 시간 형식으로 바꿀 것
//    val createdAt: Int,
//    val readOrNot: Boolean
//)
