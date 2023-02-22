package com.example.carrot.ui.community.post.detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carrot.R
import com.example.carrot.model.ComPostResponse
import com.example.carrot.model.CommentResponse
import com.example.carrot.ui.component.*
import com.example.carrot.ui.component.modifier.drawColoredShadow
import com.example.carrot.ui.theme.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostTopAppBar(
    onBack: () -> Unit,
    toggleMainBottomBar: () -> Unit
) {
    TopAppBar(
        title = {
            Row {
                BackIconBtn(color = Black80,onBack = onBack, toggleMainBottomBar = toggleMainBottomBar)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostBottomBar(
    postDetailViewModel: PostDetailViewModel,
    postId: Long
){
    BottomAppBar(
        modifier = Modifier
            .drawColoredShadow(),
        containerColor = Color.White,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(340.dp)
                    .padding(start = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    value = postDetailViewModel.comment.value,
                    onValueChange = {
                        postDetailViewModel.setComment(it)
                    },
                    placeholder = { Text(text = "댓글을 입력하세요", fontSize = 14.sp) },
                    textStyle = androidx.compose.ui.text.TextStyle(
                        fontSize = 14.sp
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        containerColor = Grey240
                    ),
                    shape = MaterialTheme.shapes.large
                )
            }
            val context = LocalContext.current
            TextButton(
                onClick = {
                    GlobalScope.launch {
                        postDetailViewModel.uploadComment(context, postId)
                    }
                },
                colors = ButtonDefaults.textButtonColors(
                    disabledContentColor = Grey210,
                    contentColor = Carrot
                )
            ) {
                Text(
                    text = "완료"
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetailScreen(
    postId: Long,
    postDetailViewModel: PostDetailViewModel = PostDetailViewModel(),
    onBack: () -> Unit,
    toggleMainBottomBar: () -> Unit
) {
    LaunchedEffect(Unit){
        postDetailViewModel.setPostDetailData(postId)
        toggleMainBottomBar()
    }
    Scaffold(
        topBar = { PostTopAppBar(onBack, toggleMainBottomBar) },
        content = {
            PostMetaData(
                post = postDetailViewModel.comPostDetail.value,
                postId = postId,
                postDetailViewModel
            )
        },
        bottomBar = { PostBottomBar(postDetailViewModel = postDetailViewModel, postId) }
    )
}

@Composable
fun PostMetaData(
    post: ComPostResponse,
    postId: Long,
    postDetailViewModel: PostDetailViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 60.dp, bottom = 70.dp)
    ) {
        item { PostWriterInfo(post = post) }
        item { PostContent(post = post) }
        item { PostInteraction() }
        item{ CommentList(postDetailViewModel = postDetailViewModel, postId) }
    }
}

@Composable
fun PostWriterInfo(
    post: ComPostResponse
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
                painter = painterResource(id = R.drawable.default_profile),
                contentDescription = "profile image",

                )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(200.dp)
                    .padding(start = 6.dp, top = 4.dp, bottom = 4.dp),
                Arrangement.SpaceBetween
            ) {
                Text(text = post.nickname ?: "default", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                Text(text = post.location, style = MaterialTheme.typography.labelSmall)
            }
        }
        // TODO
        Text(text = "${36.5}")
    }
}


@Composable
fun PostContent(
    post: ComPostResponse
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Text(text = post.title, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = post.content)
        Spacer(modifier = Modifier.height(10.dp))
        // TODO
        Text(text = "조회 ${post.viewcount}", color = Grey160)
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

@Composable
fun CommentList(
    postDetailViewModel: PostDetailViewModel,
    postId: Long
) {
    LaunchedEffect(Unit){
        postDetailViewModel.setCommentList(postId)
    }
    LazyColumn(
        modifier = Modifier
            .height(250.dp)
    ) {
        items(postDetailViewModel.commentList) { comment ->
            CommentCard(comment = comment)
        }
    }
}

@Composable
fun CommentCard(
    comment: CommentResponse
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column() {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier = Modifier.size(width = 30.dp, height = 30.dp),
                    painter = painterResource(id = R.drawable.default_profile),
                    contentDescription = "profile image",
                )
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 6.dp, top = 4.dp, bottom = 4.dp, end = 10.dp),
                    Arrangement.SpaceBetween
                ) {
                    Text(text = comment.username ?: "default", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                    Text(text = "${calculateTime(postTime = comment.commentTime)}분 전", style = MaterialTheme.typography.labelSmall)
                }
                Text(text = comment.content)
            }
            Log.i("POSTDETAILSCREEN", "comment : ${comment.content}")
        }
    }
}

@Preview("comment card")
@Composable
fun commentCardTest(){
    CarrotTheme {
        Surface {
            CommentCard(comment = CommentResponse(
                boardPostId = 1,
                boardCommentId = 1,
                commentTime = "2023-02-22T11:54:35.957102",
                content = "this is content",
                username = "user test"
            ))
        }
    }
}

fun calculateTime(postTime: String): String{
    val now = LocalDateTime.now()
    val substringedTime = postTime.substring(0, 26)


    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    val dateTime = LocalDateTime.parse(substringedTime, formatter)


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
//@Preview("Postscreen preview")
//@Composable
//fun PostScreenPreview(){
//    CarrotTheme {
//        Surface {
//            PostDetailScreen(post = SampleData.sampleComPost[3], onBack = {})
//        }
//    }
//}
//
//@Preview("Postscreen PostWriter")
//@Composable
//fun PostWriterPreview(){
//    CarrotTheme {
//        Surface {
//            PostWriterInfo(post = SampleData.sampleComPost[3])
//        }
//    }
//}
//
//@Preview("PostScreen PostContent")
//@Composable
//fun PostContentPreview(){
//    CarrotTheme {
//        Surface {
//            PostContent(post = SampleData.sampleComPost[3])
//        }
//    }
//}
//
//@Preview("Post Interaction preview")
//@Composable
//fun PostInteractionPreview(){
//    CarrotTheme {
//        Surface {
//            PostInteraction()
//        }
//    }
//}