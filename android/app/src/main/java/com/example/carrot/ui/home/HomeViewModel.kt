package com.example.carrot.ui.home

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.carrot.api.RetrofitClient.SalePostApiService
import com.example.carrot.model.SalePostResponse

class HomeViewModel(

): ViewModel() {
    private val _salePostList = mutableStateListOf<SalePostResponse>()
    val salePostList = _salePostList

    suspend fun setSalePostList(){
        // TODO: CHANGE TO INF SCROLL
        try {
            val response = SalePostApiService.getSalePostList(0, 10)
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
}