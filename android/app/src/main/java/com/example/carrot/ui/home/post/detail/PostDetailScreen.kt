package com.example.carrot.ui.home.post.detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.example.carrot.R
import com.example.carrot.model.SalePostResponse
import com.example.carrot.ui.component.*
import com.example.carrot.ui.component.modifier.drawColoredShadow
import com.example.carrot.ui.theme.Carrot
import com.example.carrot.ui.theme.Grey160
import com.example.carrot.ui.theme.Grey220
import com.example.carrot.ui.theme.Grey245
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO("일정 스크롤 밑으로 내일 시 투명한걸 없애고 흰색으로 바꿀 것")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostTopAppBar(
    onBack: () -> Unit,
    toggleMainBottomBar: () -> Unit
) {
    TopAppBar(
        title = {
            Row {
                BackIconBtn(Color.White, onBack, toggleMainBottomBar)
                HomeIconBtn(Color.White)
            }

        },
        actions = {
            MoreIconBtn(Color.White)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Unspecified
        )
    )
}

@Composable
fun PostDetailBottomBar(
    postDetailViewModel: PostDetailViewModel
){
    BottomAppBar(
        modifier = Modifier
            .drawColoredShadow(offsetX = 2.dp)
            .height(60.dp),
        containerColor = Color.White,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if(postDetailViewModel.likeToggle.value)
                    LikeIconBtn(postDetailViewModel.toggleLike)
                else
                    OutlinedLikeIconBtn(postDetailViewModel.toggleLike)
                Spacer(modifier = Modifier.width(16.dp))
                Divider(modifier = Modifier
                    .height(40.dp)
                    .width(1.dp), color = Grey220)
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = "${postDetailViewModel.salePostDetail.value.price}원",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(100.dp)
                    .height(40.dp),
                contentPadding = PaddingValues(1.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Carrot,
                    contentColor = Color.White
                ),
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = "채팅하기",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
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
    toggleMainBottomBar: () -> Unit,
    onBack: () -> Unit
) {
    LaunchedEffect(Unit){
        postDetailViewModel.setPostDetailData(postId)
        toggleMainBottomBar()
    }
    Scaffold(
        topBar = { PostTopAppBar(onBack, toggleMainBottomBar) },
        content = {
            PostMetaData(
                post = postDetailViewModel.salePostDetail.value,
                postDetailViewModel = postDetailViewModel
            )
        },
        bottomBar = { PostDetailBottomBar(postDetailViewModel)}
    )
}

@Composable
fun PostMetaData(
    post: SalePostResponse,
    postDetailViewModel: PostDetailViewModel
) {
    LazyColumn(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        item {
            PostImages(
                post = post,
                postDetailViewModel = postDetailViewModel
            )
        }
        item {
            PostWriterInfo(post = post)
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                color = Grey245
            )
        }
        item {
            PostTitle(post = post)
        }
        item {
            PostContent(post = post)
        }
    }
}


@Composable
fun PostImages(
    postDetailViewModel: PostDetailViewModel,
    post: SalePostResponse
) {

    val imageBitmap by postDetailViewModel.bitmap.observeAsState()

    val imageModifier = Modifier
        .fillMaxWidth()
        .height(340.dp)
        .clip(shape = MaterialTheme.shapes.extraSmall)

    if (post.salesImg == null || post.salePostId == 0L){
        Log.i("POSTDETAIL", "in if post card${post.salePostId} image : ${post.salesImg}")
        Image(
            painter = painterResource(id = R.drawable.default_article),
            contentDescription = "post title image",
            modifier = imageModifier,
            contentScale = ContentScale.Crop
        )
    } else {
        Log.i("POSTDETAIL", "in else post card${post.salePostId} image : ${post.salesImg}")
        LaunchedEffect(Unit){
            postDetailViewModel.viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    postDetailViewModel.getSalePostImage(post.salesImg)
                }
            }
        }
        imageBitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = imageModifier
            )
        }
    }
}

@Composable
fun PostWriterInfo(post: SalePostResponse) {
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
                Text(text = post.username, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                Text(text = post.location, style = MaterialTheme.typography.labelSmall)
            }
        }
        // TODO: USER 를 연결 해야 할 걸로 보임
        Text(text = "36.5")
    }

}

@Composable
fun PostTitle(post: SalePostResponse) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 10.dp)
    ) {
        Text(
            modifier = Modifier.padding(bottom = 10.dp),
            text = post.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Row {
            Text(text = post.category, color = Grey160 )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "${post.updatedAt}분 전", color = Grey160)
        }
    }
}


@Composable
fun PostContent(post: SalePostResponse) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Text(
            text = post.content,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

//@Preview("PostScreen PostContent")
//@Composable
//fun PostContentPreview(){
//    CarrotTheme {
//        Surface {
//            PostContent(post = SampleData.sampleSalePost[3])
//        }
//    }
//}


//@Preview("PostScreen test")
//@Composable
//fun PostScreenPreview() {
//    CarrotTheme {
//        Surface {
//            PostScreen(post = SampleData.sampleSalePost[3], onBack = {})
//        }
//    }
//}
//
//@Preview("PostScreen PostWriter")
//@Composable
//fun PostWriterPreview(){
//    CarrotTheme {
//        Surface {
//            PostWriterInfo(post = SampleData.sampleSalePost[3])
//        }
//    }
//}
//
//@Preview("PostScreen Post title")
//@Composable
//fun PostTitlePreview(){
//    CarrotTheme {
//        Surface {
//            PostTitle(post = SampleData.sampleSalePost[3])
//        }
//    }
//}
