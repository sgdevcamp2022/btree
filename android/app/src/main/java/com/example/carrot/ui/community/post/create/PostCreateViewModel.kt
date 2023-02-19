package com.example.carrot.ui.community.post.create

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.carrot.api.RetrofitClient.ComPostApiService
import com.example.carrot.api.RetrofitClient.authApiService
import com.example.carrot.model.ComPostRequest
import com.example.carrot.model.TokenStore

class PostCreateViewModel(

): ViewModel(){

    private val _title = mutableStateOf("")
    val title = _title
    val setTitle: (String) -> Unit = {
        _title.value = it
    }

    private val _content = mutableStateOf("")
    val content = _content
    val setContent: (String) -> Unit = {
        _content.value = it
    }

    private suspend fun requestCreateCompost(username: String){
        Log.i("CREATE COMPOST", "check!")

        val request = ComPostRequest(
            title = _title.value,
            content = _content.value,
            contentImg = "",
            username = username
        )

        try {
            val response = ComPostApiService.createComPost(request)
            when(response.code()){
                200 -> {
                    Log.i("CREATE COMPOST", "creating post success")
                }
                else -> {
                    Log.e("CREATE COMPOST", "creating post failed with request: ${response.code()}")
                }
            }
        } catch (e: Exception){
            Log.e("CREATE COMPOST", "creating post failed with call: $e")
        }
    }

    suspend fun createComPost(context: Context) {
        TokenStore(context).getAccessToken.collect{
            try {
                val getMyInfoResponse = authApiService.getMyInfo("Bearer $it")
                when(getMyInfoResponse.code()){
                    200 -> {
                        val username = getMyInfoResponse.body()?.nickname!!
                        Log.i("CREATE COMPOST", "get my info success username :  $username")
                        requestCreateCompost(username)
                    }
                    else -> {
                        Log.e("CREATE COMPOST", "get my info failed with request: ${getMyInfoResponse.code()}")
                    }
                }
            } catch (e: Exception){
                Log.e("CREATE COMPOST", "get my info failed with call: $e")
            }
        }
    }
}