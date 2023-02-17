package com.example.carrot.ui.sign.firstEntrance

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.carrot.api.RetrofitClient.authApiService
import com.example.carrot.model.ChangeNicknameRequest
import com.example.carrot.model.TokenStore

class FirstEntranceScreenViewModel(): ViewModel() {

    private val _nickname = mutableStateOf("")
    val nickname = _nickname

    fun setNickname(text: String){
        _nickname.value = text
    }

    suspend fun changeNicknameRequest(
        context: Context,
        authenticate: () -> Unit
    ){
        val tokenStore = TokenStore(context)
        tokenStore.getAccessToken.collect{
            try {
                val changeNicknameResponse = authApiService.changeNickname(
                    token = "Bearer $it",
                    new_nickname = ChangeNicknameRequest(new_nickname = _nickname.value)
                )
                when(changeNicknameResponse.code()){
                    200 -> {
                        authenticate()
                        Log.i("NICKNAME CHANGE", "nickname changed successfully")
                    }
                    else -> {
                        Log.i("NICKNAME CHANGE", "duplicated nickname")
                    }
                }
            } catch (e: Exception) {
                Log.i("NICKNAME CHANGE", "something goes wrong")
            }
        }
    }
}