package com.example.carrot.ui.myPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.carrot.ui.component.CategoryIconBtn
import com.example.carrot.ui.component.NotificationIconBtn
import com.example.carrot.ui.component.SearchIconBtn
import com.example.carrot.ui.component.SettingIconBtn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MypageTopAppBar(){
    TopAppBar(
        title = {},
        actions = {
            SettingIconBtn()
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}

@Composable
fun MyPageScreen(
) {

}