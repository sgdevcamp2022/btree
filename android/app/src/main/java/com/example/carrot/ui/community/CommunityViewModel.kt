package com.example.carrot.ui.community

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.carrot.api.RetrofitClient.ComPostApiService
import com.example.carrot.model.ComPostResponse

class CommunityViewModel(

): ViewModel() {

    private val _comPostList = mutableStateListOf<ComPostResponse>()
    val comPostList = _comPostList

    suspend fun setComPostList(){
        // TODO: CHANGE TO INF SCROLL
        try {
            val response = ComPostApiService.getComPostList(0, 10)
            when(response.code()){
                200 -> {
                    response.body()?.let { ComPostList ->
                        _comPostList.addAll(ComPostList)
                    }
                }
                else -> {
                    Log.i("CommunityScreen", "getComPostList failed : ${response.code()}")
                }
            }
        } catch (e: Exception){
            Log.e("", "")
        }
    }
}