package com.example.carrot.ui.home.post

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.carrot.api.RetrofitClient.PostUtilApiService

class PostCardViewModel: ViewModel() {

    private val _bitmap = MutableLiveData<Bitmap>()
    val bitmap : LiveData<Bitmap> = _bitmap

    suspend fun getSalePostImage(fileName: String) {
        try {
            val response =
                PostUtilApiService.getPostImage(fileName)
            val stream = response.byteStream()
            val bitmap = BitmapFactory.decodeStream(stream)
            Log.i("HOMESCREEN", "$bitmap")
            _bitmap.postValue(bitmap)

        } catch (e: Exception) {
            Log.e("HOMESCREEN", "getting image failed: $e")
        }
    }
}