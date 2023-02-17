package com.example.carrot.ui.home.post.detail

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.carrot.api.RetrofitClient.SalePostApiService
import com.example.carrot.model.SalePostResponse

class PostDetailViewModel(

): ViewModel() {
    private val _salePostDetail = mutableStateOf(SalePostResponse(
        salePostId = 0,
        category = "",
        title = "",
        username = "",
        content = "",
        isPostState = "",
        price = 0,
        chatNum = 0,
        likeNum = 0,
        location = "",
        salesImg = "",
        updatedAt = ""
    ))

    val salePostDetail = _salePostDetail

    val setSalePostDetail: (SalePostResponse) -> Unit = {
        _salePostDetail.value = it
    }

    private val _likeToggle = mutableStateOf(false)
    val likeToggle = _likeToggle

    suspend fun setPostDetailData(postId: Long){
        try {
            val response = SalePostApiService.getSalePostDetail(postId)
            when(response.code()){
                200 -> {
                    _salePostDetail.value = response.body()!!
                }
                else -> {
                    Log.i("POSTDETAIL", "post detail failed : ${response.code()}")
                }
            }
        } catch (e: Exception){
            Log.e("POSTDETAIL", "post detail failed : $e")
        }
    }

    val toggleLike:() -> Unit = {
        _likeToggle.value = !(_likeToggle.value)
    }
}