package com.example.carrot.ui.home.post

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PhotoCamera
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.carrot.ui.theme.Grey210
import com.example.carrot.ui.theme.Grey220
import com.example.carrot.ui.theme.Grey240


@Composable
fun PostCreateScreenContent(){
    Spacer(modifier = Modifier.height(20.dp))
    AddImage()
    Spacer(modifier = Modifier.height(20.dp))
    Divider(
        modifier = Modifier
            .fillMaxWidth(),
        color = Grey240
    )
    AddPostTitle()
    Divider(
        modifier = Modifier
            .fillMaxWidth(),
        color = Grey240
    )
    AddPostPrice()
    Divider(
        modifier = Modifier
            .fillMaxWidth(),
        color = Grey240
    )
    AddPostContent()
    Divider(
        modifier = Modifier
            .fillMaxWidth(),
        color = Grey240
    )
    Spacer(modifier = Modifier.height(20.dp))
    SelectTradeLocation()
}

@Composable
fun AddImage(){
    Button(
        onClick = { /*TODO*/ },
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 14.dp),
        shape = MaterialTheme.shapes.extraSmall,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Unspecified
        ),
        border = BorderStroke(1.dp, Grey220),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Rounded.PhotoCamera,
                contentDescription = "Add post Image",
                tint = Color.Black
            )
            Text(text = "0/10", color = Grey210)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPostTitle(){
    val (title, setTitle) = remember{ mutableStateOf("") }
    OutlinedTextField(
        value = title,
        onValueChange = {
            setTitle(it)
        },
        placeholder = { Text(text = "글 제목")},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.White,
            unfocusedBorderColor = Color.White,
            disabledBorderColor = Color.White,
            focusedBorderColor = Color.White,
            errorBorderColor = Color.White,
            placeholderColor = Grey210
        ),
        modifier = Modifier
            .fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPostPrice(){
    val (price, setPrice) = remember{ mutableStateOf("") }
    OutlinedTextField(
        value = price,
        onValueChange = {
            setPrice(it)
        },
        placeholder = { Text(text = "가격 (선택사항)")},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.White,
            unfocusedBorderColor = Color.White,
            disabledBorderColor = Color.White,
            focusedBorderColor = Color.White,
            errorBorderColor = Color.White,
            placeholderColor = Grey210
        ),
        modifier = Modifier
            .fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPostContent(){
    val (price, setPrice) = remember{ mutableStateOf("") }
    OutlinedTextField(
        value = price,
        onValueChange = {
            setPrice(it)
        },
        placeholder = { Text(text = "개신동에 올릴 게시글 내용을 작성해주세요. (판매 금지 물품은 게시가 제한될 수 있어요)")},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.White,
            unfocusedBorderColor = Color.White,
            disabledBorderColor = Color.White,
            focusedBorderColor = Color.White,
            errorBorderColor = Color.White,
            placeholderColor = Grey210
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}

@Composable
fun SelectTradeLocation(){
    Text("거래 희망 장소")
}