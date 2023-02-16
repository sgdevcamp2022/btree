package com.example.carrot.ui.chat

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.carrot.Home
import com.example.carrot.model.ChattingRoom
import com.example.carrot.model.SampleData
import com.example.carrot.ui.chat.chatRoom.ChattingRoomCard
import com.example.carrot.ui.component.CategoryIconBtn
import com.example.carrot.ui.component.LocationTitleBtn
import com.example.carrot.ui.component.NotificationIconBtn
import com.example.carrot.ui.component.SearchIconBtn
import com.example.carrot.ui.component.modifier.drawColoredShadow
import com.example.carrot.ui.home.HomeViewModel

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
    ChatViewModel: ChatViewModel = ChatViewModel(),
    navigateToChattingRoom: (chattingRoomId: Long) -> Unit
){
    Scaffold(
        topBar = { ChatTopAppBar() },
        content = {
            Column(
                modifier = Modifier
                    .padding(top = 60.dp, bottom = 70.dp)
                    .background(Color.White)
            ) {
                ChattingRoomList(
                    chattingRooms = SampleData.sampleChatRoom,
                    navigateToChattingRoom = navigateToChattingRoom
                )
            }
        }
    )
}

@Composable
fun ChattingRoomList(
    chattingRooms: List<ChattingRoom>,
    navigateToChattingRoom: (chattingRoomId: Long) -> Unit
) {
    LazyColumn {
        items(chattingRooms){ chattingRoom ->
            ChattingRoomCard(
                chattingRoom = chattingRoom,
                navigateToChattingRoom = navigateToChattingRoom)
        }
    }
}