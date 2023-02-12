package com.example.carrot.ui.myPage

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.carrot.api.RetrofitClient.apiService
import com.example.carrot.model.TokenStore
import kotlinx.coroutines.flow.collect

class MyPageScreenViewModel(

): ViewModel() {
    private val _nickname = mutableStateOf("")
    val nickname = _nickname

    suspend fun setNickname(context: Context) {
        val tokenStore = TokenStore(context)
        tokenStore.getAccessToken.collect{
            val myInfo = apiService.getMyInfo("Bearer $it")
            _nickname.value = myInfo.body()?.nickname!!
        }
    }
}