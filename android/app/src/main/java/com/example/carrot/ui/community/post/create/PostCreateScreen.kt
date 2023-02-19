package com.example.carrot.ui.community.post.create

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carrot.ui.component.CancelIconBtn
import com.example.carrot.ui.component.modifier.drawColoredShadow
import com.example.carrot.ui.theme.Carrot
import com.example.carrot.ui.theme.Grey210
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostCreateScreenTopAppBar(
    postCreateViewModel: PostCreateViewModel,
    onCancel: () -> Unit,
    toggleMainBottomBar: () -> Unit
){
    TopAppBar(
        modifier = Modifier
            .drawColoredShadow(offsetX = 2.dp),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                CancelIconBtn(
                    color = Color.Black,
                    onCancel = onCancel,
                    toggleMainBottomBar = toggleMainBottomBar
                )
                Spacer(modifier = Modifier.width(64.dp))
                Text(text = "동네생활 글쓰기", fontWeight = FontWeight.Black, fontSize = 18.sp)
            }
        },
        actions = {
            val context = LocalContext.current
            val coroutineScope = rememberCoroutineScope()

            TextButton(
                onClick = {
                    coroutineScope.launch {
                        postCreateViewModel.createComPost(context)
                    }
                    onCancel()
                    toggleMainBottomBar()
                },
                enabled = with(postCreateViewModel){
                    title.value != "" && content.value != ""
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
            Spacer(modifier = Modifier.width(16.dp))
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostCreateScreen(
    postCreateViewModel: PostCreateViewModel = PostCreateViewModel(),
    onCancel: () -> Unit,
    toggleMainBottomBar: () -> Unit
) {
    LaunchedEffect(Unit){
        toggleMainBottomBar()
    }
    Scaffold(
        topBar = { PostCreateScreenTopAppBar(
            postCreateViewModel = postCreateViewModel,
            onCancel = onCancel,
            toggleMainBottomBar = toggleMainBottomBar
        )},
        content = {
            PostCreateContent( postCreateViewModel )
        }
    )
}

@Composable
fun PostCreateContent(
    postCreateViewModel: PostCreateViewModel
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp)
            .background(Color.White)
    ) {
        PostCreateGuide()
        PostCreateTitle(
            title = postCreateViewModel.title.value,
            setTitle = postCreateViewModel.setTitle
        )
        PostCreateContent(
            content = postCreateViewModel.content.value,
            setContent = postCreateViewModel.setContent
        )
    }
}

@Composable
fun PostCreateGuide(){
   Card(
       modifier = Modifier
           .padding(horizontal = 16.dp)
           .fillMaxWidth()
       ,
       shape = MaterialTheme.shapes.extraSmall,
       colors = CardDefaults.cardColors(
           containerColor = Color(253, 245, 234),
           contentColor = Color(64, 42, 10)
       ),
       content = {
           Column(
               modifier = Modifier
                   .padding(10.dp)
           ) {
               Row() {
                   Text(text = "안내 ", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                   Text(text = "중고거래 관련, 명예훼손, 광고/홍보 목적의 글은 ",fontSize = 12.sp)
               }
               Text(text = "올리실 수 없어요.", fontSize = 12.sp)
           }
       }
   )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostCreateTitle(
    title: String,
    setTitle: (String) -> Unit
){
    OutlinedTextField(
        value = title,
        onValueChange = {
            setTitle(it)
        },
        placeholder = {
            Text(
                text = "제목",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.White,
            unfocusedBorderColor = Color.White,
            disabledBorderColor = Color.White,
            focusedBorderColor = Color.White,
            errorBorderColor = Color.White,
            placeholderColor = Grey210
        ),
        modifier = Modifier
            .fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostCreateContent(
    content: String,
    setContent: (String) -> Unit
){
    OutlinedTextField(
        value = content,
        onValueChange = {
            setContent(it)
        },
        // TODO : 개신동 -> 현재 위치로 바꿀 것
        placeholder = { Text( text = "개신동 관련된 질문이나 이야기를 해보세요" ) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.White,
            unfocusedBorderColor = Color.White,
            disabledBorderColor = Color.White,
            focusedBorderColor = Color.White,
            errorBorderColor = Color.White,
            placeholderColor = Grey210
        ),
        modifier = Modifier
            .fillMaxWidth()
    )
}