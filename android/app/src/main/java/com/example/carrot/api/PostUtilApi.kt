package com.example.carrot.api

import com.example.carrot.model.ImageResponse
import com.example.carrot.model.PostResponse
import com.example.carrot.model.PostSource
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface PostUtilApi {
    @Streaming
    @GET("/post/api/s3/download")
    suspend fun getPostImage(
        @Query("fileurl") fileurl: String
    ): ResponseBody

    @Multipart
    @POST("/post/api/s3/upload")
    suspend fun uploadPostImage(
        @Part files: MultipartBody.Part
    ): Response<ImageResponse>

    @GET("/search/{data}")
    suspend fun searchSalePost(
        @Path("data") data: String
    ): Response<PostResponse>

    @DELETE("/post/api/s3")
    suspend fun deletePostImage()

    @GET("/post/api/gps/location")
    suspend fun gpsAuth()
}