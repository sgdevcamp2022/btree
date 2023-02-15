package com.example.carrot.ui.community.post

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
import com.example.carrot.model.SampleData
import com.example.carrot.ui.theme.BlueHighLight
import com.example.carrot.ui.theme.CarrotTheme
import com.example.carrot.ui.theme.Grey160
import com.example.carrot.ui.theme.Grey245

@Composable
fun PostCard(
    post: ComPost,
    navigateToPost: (Long) -> Unit
) {
    Column(
        Modifier.background(color = Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.clickable { navigateToPost(post.postId) }
        ) {
            Column(
                Modifier
                    .height(90.dp)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Text(post.title, style = MaterialTheme.typography.labelMedium)
                    Row(
                        modifier = Modifier.padding(top = 2.dp)
                    ) {
                        Text(text = post.category, style= MaterialTheme.typography.labelSmall, color = BlueHighLight)
                        Text(text = "-", style= MaterialTheme.typography.labelSmall, color = Grey160)
                        Text(text = post.location, style= MaterialTheme.typography.labelSmall, color = Grey160)
                        Text(text = "-", style= MaterialTheme.typography.labelSmall, color = Grey160)
                        Text(text = "${post.createdAt} 분 전", style= MaterialTheme.typography.labelSmall, color = Grey160)
                    }
                }

                Row(modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                    if (post.commentNum > 0) {
                        Icon(imageVector = Icons.Outlined.Chat, contentDescription = "Chat", tint = Grey160)
                        Text(text = " ${post.commentNum} ", style = MaterialTheme.typography.bodySmall, color = Grey160)
                    }
                    if (post.likesNum > 0){
                        Icon(imageVector = Icons.Outlined.ThumbUp, contentDescription = "Favorite", tint = Grey160)
                        Text(text = " ${post.likesNum} ", style = MaterialTheme.typography.bodySmall, color = Grey160)
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

@Preview("postcard test")
@Composable
fun PreviewPostCard(){
    CarrotTheme {
        Surface {
            PostCard(
                post = SampleData.sampleComPost[1],
                navigateToPost = {}
            )
        }
    }
}