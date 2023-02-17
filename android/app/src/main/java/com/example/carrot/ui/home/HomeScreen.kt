package com.example.carrot.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.carrot.model.SalePost
import com.example.carrot.model.SampleData
import com.example.carrot.ui.component.CategoryIconBtn
import com.example.carrot.ui.component.LocationTitleBtn
import com.example.carrot.ui.component.NotificationIconBtn
import com.example.carrot.ui.component.SearchIconBtn
import com.example.carrot.ui.component.modifier.drawColoredShadow
import com.example.carrot.ui.home.post.PostCard
import com.example.carrot.ui.theme.Carrot
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
    navigateToPost: (Long) -> Unit,
    navigateToPostCreate: () -> Unit
) {
    Scaffold (
        topBar = {HomeTopAppBar()},
        content = {
                Box{
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 60.dp, bottom = 70.dp)
                            .background(Color.White)
                    ) {
                        PostList(
                            homeViewModel = homeViewModel,
                            navigateToPost = navigateToPost
                        )
                    }
                    Button(
                        onClick = { navigateToPostCreate() },
                        modifier = Modifier
                            .offset(280.dp, 640.dp),
                        contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp, start = 14.dp, end = 20.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Carrot
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 4.dp
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Add,
                            modifier = Modifier.padding(0.dp),
                            contentDescription = "write salePost"
                        )
                        Text(text = "글쓰기", fontWeight = FontWeight.Bold)
                    }
                }                
            },
        )
}

@Composable
fun PostList(
    homeViewModel: HomeViewModel,
    navigateToPost: (postId: Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit){
        homeViewModel.setSalePostList()
    }
    LazyColumn(
        modifier = modifier
    ) {
        items(homeViewModel.salePostList){ post ->
            PostCard(
                post = post,
                navigateToPost = navigateToPost
            )
        }
    }
}