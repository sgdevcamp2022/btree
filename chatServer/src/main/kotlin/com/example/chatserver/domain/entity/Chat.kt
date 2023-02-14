package com.example.chatserver.domain.entity

import com.example.chatserver.domain.enums.ContentsType
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime


@Table("chat")
data class Chat(

    @Id
    @Column("chat_id")
    val chatId: Long? = null,

    @Column("room_id")
    val roomId: Long,

    @Column("user_id")
    val userId: Long,

    @Column
    val contents: String,

    @Column("contents_type")
    val contentsType: ContentsType,

    @CreatedDate
    @Column("created_at")
    val createdAt: LocalDateTime? = null
)