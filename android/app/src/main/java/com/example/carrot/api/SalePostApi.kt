package com.example.carrot.api

import com.example.carrot.model.SalePostRequest
import com.example.carrot.model.SalePostResponse
import com.example.carrot.model.SalePostUserModel
import retrofit2.Response
import retrofit2.http.*

interface SalePostApi {
    @POST("/post/api/posts/list")
    suspend fun getSalePostList(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Body salePostUserModel: SalePostUserModel
    ): Response<List<SalePostResponse>>

    @POST("/post/api/posts")
    suspend fun createSalePost(
        @Body salePostRequest: SalePostRequest
    ): Response<Unit>

    @GET("/post/api/posts/{id}")
    suspend fun getSalePostDetail(
        @Path("id") postId: Long
    ) : Response<SalePostResponse>

    @PUT("/post/api/posts/{postId}")
    suspend fun updateSalePostDetail(
        @Path("postId") postId: Long,
        @Body salePostRequest: SalePostRequest
    ) : Response<Unit>

    @DELETE("/post/api/posts/{postId}")
    suspend fun deleteSalePost(
        @Path("postId") postId: Long
    ): Response<Unit>

    @POST("/post/api/posts/")
    suspend fun likeSalePost()
}