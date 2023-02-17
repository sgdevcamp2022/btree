package com.example.carrot.ui.home.post.create

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.carrot.api.RetrofitClient.SalePostApiService
import com.example.carrot.model.SalePostRequest

class PostCreateViewModel(

):ViewModel() {
    private val _title = mutableStateOf("")
    val title = _title
    val setTitle:(String) -> Unit = {
        _title.value = it
    }

    private val _price = mutableStateOf("")
    val price = _price
    val setPrice:(String) -> Unit = {
        _price.value = it
    }

    private val _contents = mutableStateOf("")
    val contents = _contents
    val setContents:(String) -> Unit = {
        _contents.value = it
    }

    val imagesUris = mutableStateListOf<Uri?>()
    val setImageUris:(List<Uri>) -> Unit = {
        imagesUris.addAll(it)
    }


    suspend fun createSalePost(){
        val salePostRequest = SalePostRequest(
            category = "test",
            title = _title.value,
            content = _contents.value,
            price = _price.value.toInt(),
            salesImg = "null",
            isPostState = "RESERVE"
        )
        try {
            val response = SalePostApiService.createSalePost(salePostRequest = salePostRequest)
            when(response.code()){
                200 -> Log.i("CREATE SALEPOST", "create sale post success")
                else -> Log.i("CREATE SALEPOST", "create sale post failed : ${response.code()}")
            }
        } catch (e: Exception){
            Log.i("CREATE SALEPOST", "create sale post failed : $e")
        }
    }
}