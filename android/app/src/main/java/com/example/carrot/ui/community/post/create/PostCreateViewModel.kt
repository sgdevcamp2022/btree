package com.example.carrot.ui.community.post.create

import android.content.Context
import android.media.session.MediaSession.Token
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.carrot.api.RetrofitClient
import com.example.carrot.api.RetrofitClient.ComPostApiService
import com.example.carrot.api.RetrofitClient.authApiService
import com.example.carrot.model.ComPostRequest
import com.example.carrot.model.TokenStore
import kotlinx.coroutines.flow.collect

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

    suspend fun requestCreateCompost(context: Context){
        val tokenStore = TokenStore(context)
        tokenStore.getAccessToken.collect{
            try {
                val userResponse = authApiService.getMyInfo("Bearer $it")
                when(userResponse.code()){
                    200 -> {
                        val request = ComPostRequest(
                            title = _title.value,
                            content = _content.value,
                            contentImg = "",
                            nickname = userResponse.body()?.nickname!!,
                            userEmail = userResponse.body()?.email!!,
                            location = userResponse.body()?.locate ?: "개신동",
                            gpsauth = true
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
                    else -> {
                        Log.e("CREATE COMPOST", "responsing user profile failed: ${userResponse.code()}")
                    }
                }
            } catch (e: Exception) {
                Log.e("CREATE COMPOST", "get user profile failed: $e")
            }
        }
    }
}