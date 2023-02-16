package com.example.carrot.ui.chat.chatRoom

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.carrot.model.Chat
import com.example.carrot.model.SampleData
import com.example.carrot.ui.component.BackIconBtn
import com.example.carrot.ui.component.HomeIconBtn
import com.example.carrot.ui.component.MoreIconBtn
import com.example.carrot.ui.component.modifier.drawColoredShadow
import com.example.carrot.ui.theme.Carrot
import com.example.carrot.ui.theme.CarrotTheme


// TODO: 채팅 통신이랑 같이 하는게 더 편해보임.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatRoomTopAppBar(
    onBack:() -> Unit
) {
    TopAppBar(
        modifier = Modifier
            .drawColoredShadow(offsetX = 2.dp),
        title = {
            Row {
                BackIconBtn(Color.White, onBack)
            }

        },
        actions = {
            MoreIconBtn(Color.White)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatRoomScreen(
    chats: List<Chat>,
    chatRoomViewModel: ChatRoomViewModel = ChatRoomViewModel(),
    onBack: () -> Unit
) {
    Scaffold(
        topBar = { ChatRoomTopAppBar(onBack)},
        content = {
            ChatList(chats = chats)
        }
    )
}

@Composable
fun ChatList(
    chats: List<Chat>
) {

}

@Composable
fun MyChat(
    chat: Chat
) {
    Row(

    ) {
        Column() {
            Text(text = if (chat.readOrNot) " " else "읽음")
            Text(text = "오전" + chat.createdAt.toString())
        }
        Surface(
            modifier = Modifier
                .background(Carrot),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = chat.content,
                color = Color.White
            )
        }
    }
}

@Preview("MyChat Preview")
@Composable
fun MyChatPreview() {
    CarrotTheme {
        Surface {
            MyChat(chat = SampleData.sampleChatRoom[2].chatList[3])
        }
    }
}

@Composable
fun OtherChat(
    chat: Chat
) {

}