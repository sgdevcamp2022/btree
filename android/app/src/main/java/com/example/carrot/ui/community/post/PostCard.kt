package com.example.carrot.ui.community.post

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.carrot.model.ComPost
import com.example.carrot.model.ComPostResponse
import com.example.carrot.model.SampleData
import com.example.carrot.ui.theme.BlueHighLight
import com.example.carrot.ui.theme.CarrotTheme
import com.example.carrot.ui.theme.Grey160
import com.example.carrot.ui.theme.Grey245
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun PostCard(
    post: ComPostResponse,
    navigateToPost: (Long) -> Unit
) {
    val rowHeight = if (post.commentNum > 0) 90 else 70
    Column(
        Modifier.background(color = Color.White).fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .clickable { navigateToPost(post.comPostId) }
                .fillMaxWidth()
        ) {
            Column(
                Modifier
                    .height(rowHeight.dp)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(post.title, style = MaterialTheme.typography.labelMedium)
                    Row(
                        modifier = Modifier.padding(vertical = 2.dp)
                    ) {
                        // TODO : 카테고리 추가 후 수정
                        Text(text = "개발", style= MaterialTheme.typography.labelSmall, color = BlueHighLight)
                        Text(text = "•", style= MaterialTheme.typography.labelSmall, color = Grey160)
                        Text(text = post.location, style= MaterialTheme.typography.labelSmall, color = Grey160)
                        Text(text = "•", style= MaterialTheme.typography.labelSmall, color = Grey160)
                        Text(text = "${calculateTime(post.updatedAt)}분 전", style= MaterialTheme.typography.labelSmall, color = Grey160)
                    }
                }

                if (post.commentNum > 0) {
                    Row(
                        modifier = Modifier
                            .height(16.dp)
                            .fillMaxWidth(), horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Chat,
                            contentDescription = "Chat",
                            tint = Grey160
                        )
                        Text(
                            text = " ${post.commentNum} ",
                            style = MaterialTheme.typography.bodySmall,
                            color = Grey160
                        )

                        // TODO: 좋아요 수 추가 후 수정
//                    if (post.likesNum > 0){
//                        Icon(imageVector = Icons.Outlined.ThumbUp, contentDescription = "Favorite", tint = Grey160)
//                        Text(text = " ${post.likesNum} ", style = MaterialTheme.typography.bodySmall, color = Grey160)
//                    }
                    }
                }
            }
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            color = Grey245
        )
    }
}

fun calculateTime(postTime: String): String{
    val now = LocalDateTime.now()
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    val searchFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'")
    val dateTime = try {
        LocalDateTime.parse(postTime, formatter).plusHours(9)
    } catch (e: Exception) {
        LocalDateTime.parse(postTime, searchFormatter).plusHours(9)
    }

    val duration = Duration.between(
        dateTime,
        now
    )
    Log.i("TIME", "post time : $postTime")
    Log.i("TIME", "now : $now")
    Log.i("TIME", "hours : ${duration.toHours()}")
    Log.i("TIME", "minutes : ${duration.toMinutes()}")
    Log.i("TIME", "seconds : ${duration.seconds}")
    return duration.toMinutes().toString()
}
//
//@Preview("postcard test")
//@Composable
//fun PreviewPostCard(){
//    CarrotTheme {
//        Surface {
//            PostCard(
//                post = SampleData.sampleComPost[1],
//                navigateToPost = {}
//            )
//        }
//    }
//}