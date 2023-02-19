package com.example.carrot.api

import com.example.carrot.model.ComPostRequest
import com.example.carrot.model.ComPostResponse
import retrofit2.Response
import retrofit2.http.*

interface ComPostApi {
    @GET("/post/api/board")
    suspend fun getComPostList(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<List<ComPostResponse>>

    @POST("/post/api/board")
    suspend fun createComPost(
        @Body comPostRequest: ComPostRequest
    ): Response<Unit>

    @GET("/post/api/board/{id}")
    suspend fun getComPostDetail(
        @Path("id") postId: Long
    ): Response<ComPostResponse>

    @PUT("/post/api/board")
    suspend fun updateComPostDetail()

    @DELETE("/post/api/board")
    suspend fun deleteComPost()

    @POST("/post/api/board/comment")
    suspend fun createComPostComment()

    @DELETE("/post/api/board/comment")
    suspend fun deleteComPostComment()

    @POST("/post/api/board")
    suspend fun likeComPost()
}