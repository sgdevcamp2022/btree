package com.example.carrot.api

import com.example.carrot.model.*
import retrofit2.Response
import retrofit2.http.*

interface ComPostApi {
    @POST("/post/api/board/list")
    suspend fun getComPostList(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Body comPostUserModel: ComPostUserModel
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

    @GET("/post/api/board/comment/{id}")
    suspend fun getCommentList(
        @Path("id") postId: Long,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<List<CommentResponse>>

    @POST("/post/api/board/comment/{id}")
    suspend fun createComPostComment(
        @Path("id") postId: Long,
        @Body commentRequest: CommentRequest
    ): Response<CommentResponse>

    @DELETE("/post/api/board/comment")
    suspend fun deleteComPostComment()

    @POST("/post/api/board")
    suspend fun likeComPost()
}