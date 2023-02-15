package com.example.carrot.ui.home.post

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.carrot.model.SalePost
import com.example.carrot.model.SampleData
import com.example.carrot.ui.theme.*

@Composable
fun PostCard(
    post: SalePost,
    navigateToPost: (Long) -> Unit
){
    Column(
        Modifier.background(color = Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.clickable { navigateToPost(post.postId) }
        ) {
            Image(
                painter = painterResource(post.titleImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(16.dp)
                    .size(80.dp, 80.dp)
                    .clip(MaterialTheme.shapes.small)
            )
            Column(
                Modifier
                    .height(80.dp)
                    .padding(end = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(post.title, style = MaterialTheme.typography.labelMedium)
                Row {
                    Text(text = post.location, style=MaterialTheme.typography.labelSmall, color = Grey160)
                    Text(text = "-", style=MaterialTheme.typography.labelSmall, color = Grey160)
                    Text(text = "${post.createdAt} 분 전", style=MaterialTheme.typography.labelSmall, color = Grey160)
                }
                Text(text = "${post.price}원", style = MaterialTheme.typography.titleSmall)
                Row(modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    if (post.contactNum > 0) {
                        Icon(imageVector = Icons.Outlined.Chat, contentDescription = "Chat", tint = Grey160)
                        Text(text = " ${post.contactNum} ", style = MaterialTheme.typography.bodySmall, color = Grey160)
                    }
                    if (post.likeNum > 0){
                        Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "Favorite", tint = Grey160)
                        Text(text = " ${post.likeNum} ", style = MaterialTheme.typography.bodySmall, color = Grey160)
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
                post = SampleData.sampleSalePost[1],
                navigateToPost = {}
            )
        }
    }
}