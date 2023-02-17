package com.example.carrot.api

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface ComPostApi {
    @GET("/post/api/board")
    suspend fun getComPostList()

    @POST("/post/api/board")
    suspend fun createComPost()

    @POST("/post/api/board")
    suspend fun likeComPost()

    @GET("/post/api/board")
    suspend fun getComPostDetail()

    @PUT("/post/api/board")
    suspend fun updateComPostDetail()

    @DELETE("/post/api/board")
    suspend fun deleteComPost()

    @POST("/post/api/board/comment")
    suspend fun createComPostComment()

    @DELETE("/post/api/board/comment")
    suspend fun deleteComPostComment()
}