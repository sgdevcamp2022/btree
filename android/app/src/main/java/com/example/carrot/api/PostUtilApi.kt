package com.example.carrot.api

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface PostUtilApi {
    @GET("/post/api/s3/download")
    suspend fun getPostImage()

    @POST("/post/api/s3/upload")
    suspend fun uploadPostImage()

    @DELETE("/post/api/s3")
    suspend fun deletePostImage()

    @GET("/post/api/gps/location")
    suspend fun gpsAuth()
}