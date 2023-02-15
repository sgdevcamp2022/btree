package com.example.carrot.api

import com.example.carrot.model.*
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface AuthApi {

    @POST("/auth/register")
    suspend fun signUp(@Body signUpRequest: SignUpRequest):Response<SignUpResponse>

    @POST("/auth/email_verify")
    suspend fun sendVerificationEmail(@Body email: String):Response<EmailVerifResponse>

    @FormUrlEncoded
    @POST("/auth/token")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ):Response<LogInResponse>


    @GET("/auth/profile/")
    suspend fun getMyInfo(
        @Header("Authorization") token: String
    ):Response<GetMyInfoResponse>

    @POST("/auth/update_nickname/")
    suspend fun changeNickname(
        @Header("Authorization") token: String,
        @Body new_nickname: ChangeNicknameRequest
    ): Response<GetMyInfoResponse>
}