package com.example.carrot.api

import com.example.carrot.model.ImageResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface PostUtilApi {
    @GET("/post/api/s3/download")
    suspend fun getPostImage()

    @Multipart
    @POST("/post/api/s3/upload")
    suspend fun uploadPostImage(
        @Part files: MultipartBody.Part
    ): Response<ImageResponse>

    @DELETE("/post/api/s3")
    suspend fun deletePostImage()

    @GET("/post/api/gps/location")
    suspend fun gpsAuth()
}