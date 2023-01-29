package com.example.carrot.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.HomeMax
import androidx.compose.material.icons.outlined.HomeMini
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LocationTitleBtn() {

    var onClicked: MutableState<Boolean> = remember{ mutableStateOf(false) }

    IconButton(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {
            onClicked.value = !(onClicked.value)
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(start = 12.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "개신동")
            if (onClicked.value){
                Icon(
                    imageVector = Icons.Rounded.ExpandLess,
                    contentDescription = "Need help"
                )
            } else {
                Icon(
                    imageVector = Icons.Rounded.ExpandMore,
                    contentDescription = "Need help"
                )
            }
        }
    }
}



@Composable
fun SearchIconBtn(){
    IconButton(onClick = { /* doSomething() */ }) {
        Icon(
            imageVector = Icons.Rounded.Search,
            contentDescription = "Need help"
        )
    }
}

@Composable
fun CategoryIconBtn(){
    IconButton(onClick = { /* doSomething() */ }) {
        Icon(
            imageVector = Icons.Rounded.List,
            contentDescription = "Category"
        )
    }
}

@Composable
fun NotificationIconBtn(){
    IconButton(onClick = { /* doSomething() */ }) {
        Icon(
            imageVector = Icons.Outlined.Notifications,
            contentDescription = "Notification"
        )
    }
}

@Composable
fun MoreIconBtn(color: Color){
    IconButton(onClick = { /* doSomething() */ }) {
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = "More Info",
            tint = color
        )
    }
}

@Composable
fun BackIconBtn(
    color: Color,
    onBack: () -> Unit
){
    IconButton(onClick = { onBack() }) {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = "Go Back",
            tint = color
        )
    }
}

@Composable
fun HomeIconBtn(color: Color){
    IconButton(onClick = { /* doSomething() */ }) {
        Icon(
            imageVector = Icons.Outlined.Home,
            contentDescription = "Go Home",
            tint = color
        )
    }
}