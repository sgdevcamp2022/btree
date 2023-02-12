package com.example.carrot.ui.sign.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.carrot.api.RetrofitClient.apiService
import com.example.carrot.model.SignUpRequest

class SignUpScreenViewModel(): ViewModel() {
    private val _email = mutableStateOf("ssk56545442@gmail.com")
    val email = _email

    private val _password = mutableStateOf("4865")
    val password = _password

    private val _dialogToggle = mutableStateOf(false)
    val dialogToggle = _dialogToggle

    private val _dialogTitle = mutableStateOf("")
    val dialogTitle = _dialogTitle

    private val _dialogContent = mutableStateOf("")
    val dialogContent = _dialogContent

    fun setEmail(text: String) {
        _email.value = text
    }

    fun setPassword(text: String) {
        _password.value = text
    }

    val setDialogToggle:() -> Unit = {
        _dialogToggle.value = !(_dialogToggle.value)
        Log.i("SIGNUP", "toggled : ${_dialogToggle.value}")
    }

    fun setDialog(title: String, content: String){
        _dialogTitle.value = title
        _dialogContent.value = content
    }

    suspend fun signupReguest(){

        val signUpRequest = SignUpRequest(
            email = email.value,
            password = password.value
        )

        try {
            val registerResponse = apiService.signUp(signUpRequest = signUpRequest)
            when (registerResponse.code()){
                200 -> {
                    Log.i("SIGNUP", "sign up result : ${registerResponse.body()?.email}")
                    setDialog(
                        title = "성공",
                        content = "이메일에서 인증메일을 확인 후, 로그인창으로 이동해서 로그인을 해주세요."
                    )
                    setDialogToggle()
                    emailVerification()
                }
                400 -> {
                    Log.i("SIGNUP", "email already using")
                    setDialog(
                        title = "실패",
                        content = "중복된 이메일 입니다."
                    )
                    setDialogToggle()
                }
                else -> {
                    Log.i("SIGNUP", "not found exception : ${registerResponse.code()}")
                }
            }
        } catch (e: Exception) {
            Log.i("SIGNUP", "sign up failed : $e")
        }
    }

    suspend fun emailVerification(){
        try {
            val emailVerification = apiService.sendVerificationEmail(email = email.value)
        } catch(e: Exception) {
            Log.i("SIGNUP", "email verification failed : $e")
        }
    }
}