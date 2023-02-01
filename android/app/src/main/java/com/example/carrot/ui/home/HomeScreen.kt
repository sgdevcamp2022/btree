package com.example.carrot.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.carrot.model.SalePost
import com.example.carrot.model.SampleData
import com.example.carrot.ui.component.CategoryIconBtn
import com.example.carrot.ui.component.LocationTitleBtn
import com.example.carrot.ui.component.NotificationIconBtn
import com.example.carrot.ui.component.SearchIconBtn
import com.example.carrot.ui.component.modifier.drawColoredShadow
import com.example.carrot.ui.home.post.PostCard
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar() {
    TopAppBar(
        modifier = Modifier
            .drawColoredShadow(offsetX = 2.dp),
        title = {
            LocationTitleBtn()
        },
        actions = {
            SearchIconBtn()
            CategoryIconBtn()
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
fun HomeScreen(
    homeViewModel: HomeViewModel = HomeViewModel(),
    navigateToPost: (Long) -> Unit
) {
    Scaffold (
        topBar = {HomeTopAppBar()},
        content = {
                Column(
                    modifier = Modifier.padding(top = 60.dp, bottom = 70.dp)
                ) {
                    PostList(
                        posts = SampleData.sampleSalePost,
                        navigateToPost = navigateToPost
                    )
                }
            }
        )
}

@Composable
fun PostList(
    posts: List<SalePost>,
    navigateToPost: (postId: Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(posts){ post ->
            PostCard(
                post = post,
                navigateToPost = navigateToPost
            )
        }
    }
}