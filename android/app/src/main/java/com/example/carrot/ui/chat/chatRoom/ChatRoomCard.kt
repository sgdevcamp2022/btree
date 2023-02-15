package com.example.carrot.ui.chat.chatRoom

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.carrot.model.ChattingRoom
import com.example.carrot.model.SampleData
import com.example.carrot.ui.theme.CarrotTheme
import com.example.carrot.ui.theme.Grey160
import com.example.carrot.ui.theme.Grey230

@Composable
fun ChattingRoomCard(
    chattingRoom: ChattingRoom,
    navigateToChattingRoom: (chattingRoomId: Long) -> Unit
){
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Image(
                    modifier = Modifier
                        .size(width = 40.dp, height = 40.dp),
                    painter = painterResource(id = chattingRoom.user.profileImage),
                    contentDescription = "other's profile"
                )
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Row() {
                        Text(
                            text = chattingRoom.user.name + " ",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = chattingRoom.user.location + " ",
                            style = MaterialTheme.typography.bodySmall,
                            color = Grey160
                        )
                        Text(
                            text = "${chattingRoom.updatedAt}분 전",
                            style = MaterialTheme.typography.bodySmall,
                            color = Grey160
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = chattingRoom.recentChat,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }

            Image(
                modifier = Modifier
                    .size(width = 40.dp, height = 40.dp)
                    .clip(MaterialTheme.shapes.extraSmall),
                painter = painterResource(id = chattingRoom.post.titleImage),
                contentScale = ContentScale.Crop,
                contentDescription = "other's profile"
            )
        }
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Grey230
        )
    }
}

@Preview("chattingRoomCard preview")
@Composable
fun ChattingRoomCardPreview(){
    CarrotTheme {
        Surface {
            ChattingRoomCard(
                chattingRoom = SampleData.sampleChatRoom[3],
                navigateToChattingRoom = {}
            )
        }
    }
}