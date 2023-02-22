package com.example.carrot

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.carrot.api.RetrofitClient.authApiService
import com.example.carrot.model.TokenStore

class AuthenticateViewModel(

):ViewModel() {
    private val _authenticated = mutableStateOf(false)
    val authenticated = _authenticated

    private val _bottomBarToggle = mutableStateOf(true)
    val bottomBarToggle = _bottomBarToggle

    val authenticate: () -> Unit = {
        _authenticated.value = !(_authenticated.value)
    }

    val toggleMainBottomBar: () -> Unit = {
        _bottomBarToggle.value = !(_bottomBarToggle.value)
    }

    // TODO : generate_access_token 오류 해결 시 다시 시작
    suspend fun setAuthenticate(context : Context){
        val tokenStore = TokenStore(context)
        tokenStore.getAccessToken.collect{
            try {
                val response = authApiService.getMyInfo("Bearer $it")
                when(response.code()){

                }
            } catch (e: Exception){
                Log.e("MAIN", "request failed : $e")
            }
        }
    }
}