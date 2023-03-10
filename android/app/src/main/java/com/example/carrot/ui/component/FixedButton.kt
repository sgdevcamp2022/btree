package com.example.carrot.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.carrot.ui.theme.Carrot
import com.example.carrot.ui.theme.Grey160

@Composable
fun LocationTitleBtn() {

    var onClicked: MutableState<Boolean> = remember{ mutableStateOf(false) }

    IconButton(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {
            onClicked.value = !(onClicked.value)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "개신동")
            if (onClicked.value){
                Icon(
                    imageVector = Icons.Rounded.ExpandLess,
                    contentDescription = "Need help"
                )
            } else {
                Icon(
                    imageVector = Icons.Rounded.ExpandMore,
                    contentDescription = "Need help"
                )
            }
        }
    }
}

@Composable
fun LikeBtnWithText(){

    IconButton(
        modifier = Modifier.width(100.dp),
        onClick = { /*TODO*/ }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.ThumbUp,
                contentDescription = "LIKE",
                tint = Grey160
            )
            Text(text = "공감하기", color = Grey160)
        }
    }
}

@Composable
fun CommentBtnWithText(){

    IconButton(
        modifier = Modifier.width(100.dp),
        onClick = { /*TODO*/ }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Comment,
                contentDescription = "COMMENT",
                tint = Grey160
            )
            Text(text = "댓글", color = Grey160)
        }
    }
}

@Composable
fun FavoriteBtnWithText(){

    IconButton(
        modifier = Modifier.width(100.dp),
        onClick = { /*TODO*/ }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "FAVORITE",
                tint = Grey160
            )
            Text(text = "관심", color = Grey160)
        }
    }
}


@Composable
fun SearchIconBtn(
){
    IconButton(onClick = {  }) {
        Icon(
            imageVector = Icons.Rounded.Search,
            contentDescription = "Need help"
        )
    }
}

@Composable
fun SearchIconBtn(
    navigateToSearch: () -> Unit
){
    IconButton(onClick = { navigateToSearch() }) {
        Icon(
            imageVector = Icons.Rounded.Search,
            contentDescription = "Need help"
        )
    }
}

@Composable
fun CategoryIconBtn(){
    IconButton(onClick = { /* doSomething() */ }) {
        Icon(
            imageVector = Icons.Rounded.Menu,
            contentDescription = "Category"
        )
    }
}

@Composable
fun MyInfoIconBtn(){
    IconButton(onClick = { /* doSomething() */ }) {
        Icon(
            imageVector = Icons.Outlined.AccountCircle,
            contentDescription = "Category"
        )
    }
}

@Composable
fun NotificationIconBtn(){
    IconButton(onClick = { /* doSomething() */ }) {
        Icon(
            imageVector = Icons.Outlined.Notifications,
            contentDescription = "Notification"
        )
    }
}

@Composable
fun MoreIconBtn(color: Color){
    IconButton(onClick = { /* doSomething() */ }) {
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = "More Info",
            tint = color
        )
    }
}

@Composable
fun BackIconBtn(
    color: Color,
    onBack: () -> Unit
){
    IconButton(onClick = { onBack() }) {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = "Go Back",
            tint = color
        )
    }
}

@Composable
fun BackIconBtn(
    color: Color,
    onBack: () -> Unit,
    toggleMainBottomBar: () -> Unit
){
    IconButton(onClick = {
        onBack()
        toggleMainBottomBar()
    }) {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = "Go Back",
            tint = color
        )
    }
}

@Composable
fun CancelIconBtn(
    color: Color,
    onCancel: () -> Unit
){
    IconButton(onClick = { onCancel() }) {
        Icon(
            imageVector = Icons.Rounded.Close,
            contentDescription = "Go Back",
            tint = color
        )
    }
}

@Composable
fun CancelIconBtn(
    color: Color,
    onCancel: () -> Unit,
    toggleMainBottomBar: () -> Unit
){
    IconButton(onClick = {
        onCancel()
        toggleMainBottomBar()
    }) {
        Icon(
            imageVector = Icons.Rounded.Close,
            contentDescription = "Go Back",
            tint = color
        )
    }
}

@Composable
fun HomeIconBtn(color: Color){
    IconButton(onClick = { /* doSomething() */ }) {
        Icon(
            imageVector = Icons.Outlined.Home,
            contentDescription = "Go Home",
            tint = color
        )
    }
}

@Composable
fun SettingIconBtn(){
    IconButton(onClick = { /* doSomething() */ }) {
        Icon(
            imageVector = Icons.Outlined.Settings,
            contentDescription = "Settings"
        )
    }
}

@Composable
fun OutlinedLikeIconBtn(toggle: () -> Unit){
    IconButton(onClick = { toggle() }) {
        Icon(
            imageVector = Icons.Outlined.FavoriteBorder,
            contentDescription = "Outlined Like Button",
        )
    }
}

@Composable
fun LikeIconBtn(toggle: () -> Unit){
    IconButton(onClick = { toggle() }) {
        Icon(
            imageVector = Icons.Outlined.Favorite,
            contentDescription = "Like Button",
            tint = Carrot
        )
    }
}