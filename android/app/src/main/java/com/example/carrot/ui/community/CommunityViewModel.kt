package com.example.carrot.ui.community

import android.content.Context
import android.media.session.MediaSession.Token
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.carrot.api.RetrofitClient.ComPostApiService
import com.example.carrot.api.RetrofitClient.authApiService
import com.example.carrot.model.ComPostResponse
import com.example.carrot.model.ComPostUserModel
import com.example.carrot.model.TokenStore
import kotlinx.coroutines.flow.collect

class CommunityViewModel(

): ViewModel() {

    private val _comPostList = mutableStateListOf<ComPostResponse>()
    val comPostList = _comPostList

    suspend fun setComPostList(context: Context){
        // TODO: CHANGE TO INF SCROLL

        val tokenStore = TokenStore(context)
        tokenStore.getAccessToken.collect{
            try {
                val userResponse = authApiService.getMyInfo(("Bearer $it"))
                when(userResponse.code()){
                    200 -> {
                        val comPostUserModel = with(userResponse.body()){
                            ComPostUserModel(
                                gpsauth = true,
                                locate = "개신동",
                                nickname = this!!.nickname,
                                useremail = this.email
                            )
                        }

                        try {
                            val response = ComPostApiService.getComPostList(0, 10, comPostUserModel)
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
                            Log.e("CommunityScreen", "setting compost failed : $e")
                        }
                    }
                    else -> {
                        Log.e("CommunityScreen", "responsing user profile failed: ${userResponse.code()}")
                    }
                }
            } catch (e: Exception){
                Log.e("CommunityScreen", "get user profile failed: $e")
            }
        }
    }
}