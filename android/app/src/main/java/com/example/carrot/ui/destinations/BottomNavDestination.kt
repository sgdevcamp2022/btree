package com.example.carrot

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person2
import androidx.compose.material.icons.rounded.Chat
import androidx.compose.material.icons.rounded.Description
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person2
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.carrot.ui.chat.ChatScreen
import com.example.carrot.ui.community.CommunityScreen
import com.example.carrot.ui.myPage.MyPageScreen

object Home {
    val icon: ImageVector = Icons.Outlined.Home
    val selectedIcon: ImageVector = Icons.Rounded.Home
}

object Community {
    val icon: ImageVector = Icons.Outlined.Description
    val selectedIcon: ImageVector = Icons.Rounded.Description
}

object Chat {
    val icon: ImageVector = Icons.Outlined.Chat
    val selectedIcon: ImageVector = Icons.Rounded.Chat
}

object MyPage {
    val icon: ImageVector = Icons.Outlined.Person2
    val selectedIcon: ImageVector = Icons.Rounded.Person2
}