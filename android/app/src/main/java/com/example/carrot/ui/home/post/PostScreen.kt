package com.example.carrot.ui.home.post

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.carrot.model.SalePost
import com.example.carrot.model.SampleData
import com.example.carrot.ui.component.*
import com.example.carrot.ui.component.modifier.drawColoredShadow
import com.example.carrot.ui.theme.CarrotTheme
import com.example.carrot.ui.theme.Grey160
import com.example.carrot.ui.theme.Grey245
import com.example.carrot.ui.theme.transparent000
import com.google.accompanist.systemuicontroller.rememberSystemUiController

// TODO("일정 스크롤 밑으로 내일 시 투명한걸 없애고 흰색으로 바꿀 것")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostTopAppBar(
    onBack: () -> Unit
) {
    TopAppBar(
        title = {
            Row {
                BackIconBtn(Color.White, onBack)
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(
    post: SalePost,
    postViewModel: PostViewModel = PostViewModel(),
    onBack: () -> Unit
) {
    Scaffold(
        topBar = { PostTopAppBar(onBack) },
        content = {
            PostMetaData(
                post = post
            )
        },
    )
}

@Composable
fun PostMetaData(
    post: SalePost
) {
    LazyColumn(
        modifier = Modifier.background(Color.White)
    ) {
        item {
            PostImages(post = post)
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

@Preview("PostScreen test")
@Composable
fun PostScreenPreview() {
    CarrotTheme {
        Surface {
            PostScreen(post = SampleData.sampleSalePost[3], onBack = {})
        }
    }
}

@Composable
fun PostImages(post: SalePost) {
    val imageModifier = Modifier
        .fillMaxWidth()
        .height(340.dp)
        .clip(shape = MaterialTheme.shapes.extraSmall)
    Image(
        painter = painterResource(id = post.titleImage),
        contentDescription = "post title image",
        modifier = imageModifier,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun PostWriterInfo(post: SalePost) {
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

@Preview("PostScreen PostWriter")
@Composable
fun PostWriterPreview(){
    CarrotTheme {
        Surface {
            PostWriterInfo(post = SampleData.sampleSalePost[3])
        }
    }
}

@Composable
fun PostTitle(post: SalePost) {
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
            Text(text = "${post.createdAt}분 전", color = Grey160)
        }
    }
}

@Preview("PostScreen Post title")
@Composable
fun PostTitlePreview(){
    CarrotTheme {
        Surface {
            PostTitle(post = SampleData.sampleSalePost[3])
        }
    }
}


@Composable
fun PostContent(post: SalePost) {
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

@Preview("PostScreen PostContent")
@Composable
fun PostContentPreview(){
    CarrotTheme {
        Surface {
            PostContent(post = SampleData.sampleSalePost[3])
        }
    }
}


