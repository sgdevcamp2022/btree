package com.example.carrot

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AuthenticateViewModel(

):ViewModel() {
    private val _authenticated = mutableStateOf(false)
    val authenticated = _authenticated

    val authenticate: () -> Unit = {
        _authenticated.value = true
    }
}