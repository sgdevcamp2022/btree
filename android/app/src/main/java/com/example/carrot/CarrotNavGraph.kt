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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.carrot.ui.chat.ChatScreen
import com.example.carrot.ui.community.CommunityScreen
import com.example.carrot.ui.home.HomeScreen
import com.example.carrot.ui.myPage.MyPageScreen

@Composable
fun CarrotNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Home.route ){
        composable(Home.route){
            Home.screen()
        }
        composable(Community.route){
            Community.screen()
        }
        composable(Chat.route){
            Chat.screen()
        }
        composable(MyPage.route){
            MyPage.screen()
        }

    }
}

interface BottomNavDestination {
    val icon: ImageVector
    val selectedIcon: ImageVector
    val route: String
    val title: String
    val screen: @Composable () -> Unit
}

object Home: BottomNavDestination {
    override val icon: ImageVector = Icons.Outlined.Home
    override val selectedIcon: ImageVector = Icons.Rounded.Home
    override val route: String = "홈"
    override val title: String = "홈"
    override val screen: @Composable () -> Unit = { HomeScreen()}
}

object Community: BottomNavDestination {
    override val icon: ImageVector = Icons.Outlined.Description
    override val selectedIcon: ImageVector = Icons.Rounded.Description
    override val route: String = "동네생활"
    override val title: String = "동네생황"
    override val screen: @Composable () -> Unit = { CommunityScreen() }
}

object Chat: BottomNavDestination {
    override val icon: ImageVector = Icons.Outlined.Chat
    override val selectedIcon: ImageVector = Icons.Rounded.Chat
    override val route: String = "채팅"
    override val title: String = "채팅"
    override val screen: @Composable () -> Unit = { ChatScreen() }
}

object MyPage: BottomNavDestination {
    override val icon: ImageVector = Icons.Outlined.Person2
    override val selectedIcon: ImageVector = Icons.Rounded.Person2
    override val route: String = "나의 당근"
    override val title: String = "나의 당근"
    override val screen: @Composable () -> Unit = { MyPageScreen() }
}
