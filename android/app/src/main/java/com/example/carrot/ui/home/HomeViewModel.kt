package com.example.carrot.ui.home

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.carrot.R
import com.example.carrot.api.PostUtilApi
import com.example.carrot.api.RetrofitClient.PostUtilApiService
import com.example.carrot.api.RetrofitClient.SalePostApiService
import com.example.carrot.api.RetrofitClient.authApiService
import com.example.carrot.model.SalePostResponse
import com.example.carrot.model.SalePostUserModel
import com.example.carrot.model.TokenStore

class HomeViewModel(

): ViewModel() {
    private val _salePostList = mutableStateListOf<SalePostResponse>()
    val salePostList = _salePostList

    suspend fun setSalePostList(context: Context){
        // TODO: CHANGE TO INF SCROLL

        val tokenStore = TokenStore(context)
        tokenStore.getAccessToken.collect{
            try {
                val userResponse = authApiService.getMyInfo("Bearer $it")
                when(userResponse.code()){
                    200 -> {
                        val salePostUserModel = with(userResponse.body()){
                            SalePostUserModel(
                                gpsauth = true,
                                locate = "개신동",
                                nickname = this!!.nickname,
                                useremail = this.email
                            )
                        }

                        try {
                            val response = SalePostApiService.getSalePostList(0, 10, salePostUserModel)
                            when(response.code()){
                                200 -> {
                                    response.body()?.let { SalePostList ->
                                        _salePostList.addAll(SalePostList)
                                    }
                                }
                                else -> {
                                    Log.i("HOMESCREEN", "getSalePostList request(${response.code()}) failed : ${response.body()}")
                                }
                            }
                        } catch (e: Exception) {
                            Log.e("HOMESCREEN", "setting sale post failed : $e")
                        }
                    }
                    else -> {
                        Log.e("HOMESCREEN", "responsing user profile failed: ${userResponse.code()}")
                    }
                }
            } catch (e: Exception){
                Log.e("HOMESCREEN", "get user profile failed: $e")
            }

        }
    }
}