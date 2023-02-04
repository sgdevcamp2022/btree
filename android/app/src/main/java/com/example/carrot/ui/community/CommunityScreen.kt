package com.example.carrot.ui.community

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
import com.example.carrot.model.ComPost
import com.example.carrot.model.SampleData
import com.example.carrot.ui.community.post.PostCard
import com.example.carrot.ui.component.*
import com.example.carrot.ui.component.modifier.drawColoredShadow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityTopAppBar() {
    TopAppBar(
        modifier = Modifier
            .drawColoredShadow(offsetX = 2.dp),
        title = {
            LocationTitleBtn()
        },
        actions = {
            SearchIconBtn()
            MyInfoIconBtn()
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
fun CommunityScreen(
    communityViewMode: CommunityViewModel = CommunityViewModel(),
    navigateToPost: (Long) -> Unit
){
    Scaffold(
        topBar = { CommunityTopAppBar()},
        content = {
            Column(
                modifier = Modifier.padding(top = 60.dp, bottom = 70.dp)
            ) {
                PostList(
                    posts = SampleData.sampleComPost,
                    navigateToPost = navigateToPost
                )
            }
        }
    )
}

@Composable
fun PostList(
    posts: List<ComPost>,
    navigateToPost: (postId: Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(posts) { post ->
            PostCard(
                post = post,
                navigateToPost = navigateToPost
            )
        }
    }
}
