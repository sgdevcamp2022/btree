package com.example.carrot.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL: String = "http://39.121.157.64:10000"
    private val retrofit: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }
    val apiService: AuthApi by lazy {
        retrofit.build()
            .create(AuthApi::class.java)
    }
    val chatApiService: ChatApi by lazy {
        retrofit.build()
            .create(ChatApi::class.java)
    }
}