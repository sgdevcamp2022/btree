package com.example.carrot.ui.community

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.carrot.model.ComPost
import com.example.carrot.model.SampleData
import com.example.carrot.ui.community.post.PostCard
import com.example.carrot.ui.component.*
import com.example.carrot.ui.component.modifier.drawColoredShadow
import com.example.carrot.ui.theme.Carrot

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
    communityViewModel: CommunityViewModel = CommunityViewModel(),
    navigateToPost: (Long) -> Unit,
    navigateToPostCreate: () -> Unit
){
    Scaffold(
        topBar = { CommunityTopAppBar()},
        content = {
            Column(
                modifier = Modifier
                    .padding(top = 60.dp, bottom = 70.dp)
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                PostList(
                    communityViewModel = communityViewModel,
                    navigateToPost = navigateToPost
                )
            }
            Button(
                onClick = { navigateToPostCreate() },
                modifier = Modifier
                    .offset(330.dp, 640.dp)
                    .size(50.dp),
                contentPadding = PaddingValues(vertical = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Carrot
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 4.dp
                ),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    modifier = Modifier.padding(0.dp),
                    contentDescription = "write salePost"
                )
            }
        }
    )
}

@Composable
fun PostList(
    communityViewModel: CommunityViewModel,
    navigateToPost: (postId: Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit){
        communityViewModel.setComPostList()
    }
    LazyColumn(
        modifier = modifier
    ) {
        items(communityViewModel.comPostList) { post ->
            PostCard(
                post = post,
                navigateToPost = navigateToPost
            )
        }
    }
}
