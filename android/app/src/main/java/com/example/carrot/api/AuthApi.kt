package com.example.carrot.api

import retrofit2.http.POST

interface AuthApi {

    @POST("/token")
    fun login(){}

    @POST("/register")
    fun signIn(){}

}