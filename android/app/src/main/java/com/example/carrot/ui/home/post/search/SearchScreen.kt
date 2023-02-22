package com.example.carrot.ui.home.post.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carrot.ui.component.BackIconBtn
import com.example.carrot.ui.component.modifier.drawColoredShadow
import com.example.carrot.ui.home.post.PostCard
import com.example.carrot.ui.theme.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreenTopBar(
    onBack: () -> Unit,
    searchViewModel: SearchViewModel
){
    TopAppBar(
        modifier = Modifier.drawColoredShadow(),
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                BackIconBtn(Black80, onBack)
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(280.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        value = searchViewModel.searchItem.value,
                        onValueChange = {
                            searchViewModel.setSearchItem(it)
                        },
                        placeholder = { Text(text = "근처에서 검색", fontSize = 14.sp) },
                        textStyle = androidx.compose.ui.text.TextStyle(
                            fontSize = 14.sp
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White,
                            containerColor = Grey240
                        )
                    )
                }
                TextButton(
                    onClick = {
                          GlobalScope.launch {
                              searchViewModel.setSearchPostList()
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
        },
        actions = {

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel = SearchViewModel(),
    navigateToPost: (Long) -> Unit,
    onBack: () -> Unit
){
    Scaffold(
        topBar = { SearchScreenTopBar(onBack, searchViewModel)},
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 60.dp)
                    .background(Color.White)
            ) {
                SearchContent(searchViewModel, navigateToPost)
            }
        }
    )
}

@Composable
fun SearchContent(
    searchViewModel: SearchViewModel,
    navigateToPost: (Long) -> Unit
){
    LazyColumn {
        items(searchViewModel.searchPostList){ post ->
            Log.i("SEARCHSCREEN", "title : ${post.title}")
            PostCard(
                post = post,
                navigateToPost = navigateToPost
            )
        }
    }
}