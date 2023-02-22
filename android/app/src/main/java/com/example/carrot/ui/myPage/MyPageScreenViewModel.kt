package com.example.carrot.ui.myPage

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.carrot.api.RetrofitClient.authApiService
import com.example.carrot.model.TokenStore

class MyPageScreenViewModel(

): ViewModel() {
    private val _nickname = mutableStateOf("")
    val nickname = _nickname

    suspend fun setNickname(context: Context) {
        val tokenStore = TokenStore(context)
        tokenStore.getAccessToken.collect{
            val myInfo = authApiService.getMyInfo("Bearer $it")
            _nickname.value = myInfo.body()?.nickname!!
        }
    }
}