package com.example.carrot.ui.home.post

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carrot.ui.community.post.PostCreateScreen
import com.example.carrot.ui.component.*
import com.example.carrot.ui.component.modifier.drawColoredShadow
import com.example.carrot.ui.theme.Carrot

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostCreateScreenTopAppBar(
    onCancel: () -> Unit
){
    TopAppBar(
        modifier = Modifier
            .drawColoredShadow(offsetX = 2.dp),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                CancelIconBtn(
                    color = Color.Black,
                    onCancel = onCancel
                )
                Spacer(modifier = Modifier.width(100.dp))
                Text(text = "내 물건 팔기", fontWeight = FontWeight.Black, fontSize = 18.sp)
            }
        }
        ,
        actions = {
            Text(
                text = "완료",
                color = Carrot
            )
            Spacer(modifier = Modifier.width(16.dp))
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostCreateScreen(
    onCancel: () -> Unit
) {
    Scaffold(
        topBar = { PostCreateScreenTopAppBar(onCancel) },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(top = 60.dp, bottom = 70.dp, start = 16.dp, end = 16.dp)
            ) {
                PostCreateScreenContent()
            }
        }
    )
}