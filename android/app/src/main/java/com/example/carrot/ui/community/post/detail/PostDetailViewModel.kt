package com.example.carrot.ui.community.post.detail

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.carrot.api.RetrofitClient.ComPostApiService
import com.example.carrot.model.ComPostResponse

class PostDetailViewModel(

): ViewModel() {
    private val _comPostDetail = mutableStateOf(ComPostResponse(
        comPostId = 0,
        title = "",
        username = "",
        content = "",
        commentNum = 0,
        location = "",
        contentImg = "",
        updatedAt = ""
    ))
    val comPostDetail = _comPostDetail

    suspend fun setPostDetailData(postId: Long){
        try {
            val response = ComPostApiService.getComPostDetail(postId)
            when(response.code()){
                200 -> {
                    _comPostDetail.value = response.body()!!
                }
                else -> {
                    Log.i("POST DETAIL", "post detail failed : ${response.code()}")
                }
            }
        } catch (e: Exception){
            Log.e("POST DETAIL", "post detail failed : $e")
        }
    }
}