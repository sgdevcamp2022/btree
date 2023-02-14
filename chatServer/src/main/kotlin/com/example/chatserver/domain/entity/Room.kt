package com.example.chatserver.domain.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("room")
data class Room(

    @Id
    @Column("room_id")
    val roomId: Long? = null,

    @Column("buyer_email")
    val buyerEmail: String,

    @Column("buyer_name")
    val buyerName: String,

    @Column("seller_name")
    val sellerName: String,

    @Column("seller_email")
    val sellerEmail: String,

    @Column("location")
    val location: String,

    @CreatedDate
    @Column("created_at")
    val createdAt: LocalDateTime? = null,

    @LastModifiedDate
    @Column("updated_at")
    val updatedAt: LocalDateTime? = null
)
