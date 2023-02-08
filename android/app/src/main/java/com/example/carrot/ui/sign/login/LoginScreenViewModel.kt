package com.example.carrot.ui.sign.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginScreenViewModel(

): ViewModel() {
    private val _email = mutableStateOf("")
    val email = _email

    private val _password = mutableStateOf("")
    val password = _password

    fun setEmail(text: String){
        _email.value = text
    }
    fun setPassword(text: String){
        _password.value = text
    }
}