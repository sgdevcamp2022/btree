package com.example.carrot.ui.community.post

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.carrot.model.ComPost
import com.example.carrot.model.SampleData
import com.example.carrot.ui.component.*
import com.example.carrot.ui.component.modifier.drawColoredShadow
import com.example.carrot.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostTopAppBar(
    onBack: () -> Unit
) {
    TopAppBar(
        title = {
            Row {
                BackIconBtn(color = Black80,onBack = onBack)
                HomeIconBtn(color = Black80)
            }

        },
        actions = {
            MoreIconBtn(color = Black80)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(
    post: ComPost,
    postViewModel: PostViewModel = PostViewModel(),
    onBack: () -> Unit
) {
    Scaffold(
        topBar = { PostTopAppBar(onBack) },
        content = {
            PostMetaData(
                post = post
            )
        }
    )
}

@Composable
fun PostMetaData(
    post: ComPost
) {
    LazyColumn(
        modifier = Modifier
            .background(Color.White)
            .padding(top = 60.dp, bottom = 70.dp)
    ) {
        item { PostWriterInfo(post = post) }
        item { PostContent(post = post)}
        item { PostInteraction()}
    }
}

@Preview("Postscreen preview")
@Composable
fun PostScreenPreview(){
    CarrotTheme {
        Surface {
            PostScreen(post = SampleData.sampleComPost[3], onBack = {})
        }
    }
}

@Composable
fun PostWriterInfo(
    post: ComPost
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier.size(width = 40.dp, height = 40.dp),
                painter = painterResource(id = post.writer.profileImage),
                contentDescription = "profile image",

                )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(200.dp)
                    .padding(start = 6.dp, top = 4.dp, bottom = 4.dp),
                Arrangement.SpaceBetween
            ) {
                Text(text = post.writer.name, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                Text(text = post.location, style = MaterialTheme.typography.labelSmall)
            }
        }
        Text(text = post.writer.manner.toString())
    }
}

@Preview("Postscreen PostWriter")
@Composable
fun PostWriterPreview(){
    CarrotTheme {
        Surface {
            PostWriterInfo(post = SampleData.sampleComPost[3])
        }
    }
}

@Composable
fun PostContent(
    post: ComPost
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = post.title, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = post.content)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "조회 ${post.views}", color = Grey160)
    }
}

@Preview("PostScreen PostContent")
@Composable
fun PostContentPreview(){
    CarrotTheme {
        Surface {
            PostContent(post = SampleData.sampleComPost[3])
        }
    }
}

@Composable
fun PostInteraction(

) {
    Column {
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Grey230
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            LikeBtnWithText()
            CommentBtnWithText()
            FavoriteBtnWithText()
        }
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Grey230
        )
    }
}

@Preview("Post Interaction preview")
@Composable
fun PostInteractionPreview(){
    CarrotTheme {
        Surface {
            PostInteraction()
        }
    }
}