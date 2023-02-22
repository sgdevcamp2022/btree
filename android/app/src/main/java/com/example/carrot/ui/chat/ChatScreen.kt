package com.example.carrot.ui.chat

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.carrot.model.ChatRoom
import com.example.carrot.model.SampleData
import com.example.carrot.ui.chat.chatRoom.ChattingRoomCard
import com.example.carrot.ui.component.NotificationIconBtn
import com.example.carrot.ui.component.modifier.drawColoredShadow
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopAppBar() {
    TopAppBar(
        modifier = Modifier
            .drawColoredShadow(offsetX = 2.dp),
        title = {
            Text(text = "채팅")
        },
        actions = {
            NotificationIconBtn()
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    chatViewModel: ChatViewModel = ChatViewModel(),
    navigateToChattingRoom: (chattingRoomId: Long) -> Unit
){
    val context = LocalContext.current
    LaunchedEffect(Unit){
        chatViewModel.setChatRoomList(context)
    }
    Scaffold(
        topBar = { ChatTopAppBar() },
        content = {
            Column(
                modifier = Modifier
                    .padding(top = 60.dp, bottom = 70.dp)
                    .background(Color.White)
                    .fillMaxSize()
            ) {
                ChattingRoomList(
                    chatViewModel = chatViewModel,
                    navigateToChattingRoom = navigateToChattingRoom
                )
            }
        }
    )
}

@Composable
fun ChattingRoomList(
    chatViewModel: ChatViewModel,
    navigateToChattingRoom: (chattingRoomId: Long) -> Unit
) {
    LazyColumn {
        items(chatViewModel.chatRoomList){ chattingRoom ->
            ChattingRoomCard(
                chattingRoom = chattingRoom,
                navigateToChattingRoom = navigateToChattingRoom)
        }
    }
}