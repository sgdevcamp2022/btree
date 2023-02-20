package com.example.carrot.ui.home.post.detail

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carrot.api.RetrofitClient
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

    private val _bitmap = MutableLiveData<Bitmap>()
    val bitmap : LiveData<Bitmap> = _bitmap

    suspend fun getSalePostImage(fileName: String) {
        try {
            val response =
                RetrofitClient.PostUtilApiService.getPostImage(fileName)
            val stream = response.byteStream()
            val bitmap = BitmapFactory.decodeStream(stream)
            Log.i("POSTDETAIL", "$bitmap")
            _bitmap.postValue(bitmap)

        } catch (e: Exception) {
            Log.e("POSTDETAIL", "getting image failed: $e")
        }
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