package com.example.carrot.ui.sign.login

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.carrot.api.RetrofitClient.authApiService
import com.example.carrot.model.TokenStore

class LoginScreenViewModel(): ViewModel() {
    private val _email = mutableStateOf("ssk56545442@gmail.com")
    val email = _email

    private val _password = mutableStateOf("4865")
    val password = _password

    private val _firstOrNot = mutableStateOf(false)
    val firstOrNot = _firstOrNot

    fun setEmail(text: String) {
        _email.value = text
    }

    fun setPassword(text: String) {
        _password.value = text
    }

    suspend fun requestLogin(
        context: Context
    ){
        val tokenStore = TokenStore(context)

        try {
            val logInResponse = authApiService.login(username = email.value, password = password.value)

            val accessToken = logInResponse.body()?.access_token!!
            val refreshToken = logInResponse.body()?.refresh_token!!

            tokenStore.saveAccessToken(accessToken)
            tokenStore.saveRefreshToken(refreshToken)

        } catch (e: Exception){
            Log.e("LOGIN", "login failed : $e")
        }
    }

    suspend fun getUserInfo(
        context: Context,
        authenticate: () -> Unit
    ) {
        val tokenStore = TokenStore(context)
        var nickname: String = ""
        tokenStore.getAccessToken.collect{
            Log.i("MY INFO", "my info access_token : $it")
            try {
                val myInfo = authApiService.getMyInfo("Bearer $it")
                Log.i("MY INFO", "my info : ${myInfo.body()?.nickname}")

                if (myInfo.body()?.nickname == null){
                    _firstOrNot.value = true
                } else{
                    authenticate()
                }
            } catch (e: Exception){
                Log.i("MY INFO", "my info goes wrong : $e")
                e.printStackTrace()
            }
        }
    }
}