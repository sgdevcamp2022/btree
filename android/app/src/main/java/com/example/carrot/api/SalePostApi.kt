package com.example.carrot.api

import com.example.carrot.model.SalePostRequest
import com.example.carrot.model.SalePostResponse
import retrofit2.Response
import retrofit2.http.*

interface SalePostApi {
    @GET("/post/api/posts")
    suspend fun getSalePostList(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<List<SalePostResponse>>

    @POST("/post/api/posts")
    suspend fun createSalePost(
        @Body salePostRequest: SalePostRequest
    ): Response<Unit>

    @GET("/post/api/posts/{id}")
    suspend fun getSalePostDetail(
        @Path("id") postId: Long
    ) : Response<SalePostResponse>

    @PUT("/post/api/posts/")
    suspend fun updateSalePostDetail(
        @Path("postId") postId: Long,
        @Body salePostRequest: SalePostRequest
    ) : Response<Unit>

    @DELETE("/post/api/posts/")
    suspend fun deleteSalePost(
        @Path("postId") postId: Long
    ): Response<Unit>

    @POST("/post/api/posts/")
    suspend fun likeSalePost()
}