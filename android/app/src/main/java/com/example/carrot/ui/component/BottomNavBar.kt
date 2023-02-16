package com.example.carrot.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.carrot.Chat
import com.example.carrot.Community
import com.example.carrot.Home
import com.example.carrot.MyPage
import com.example.carrot.ui.component.modifier.drawColoredShadow
import com.example.carrot.ui.destinations.ChatNavDestination.CHAT_ROUTE
import com.example.carrot.ui.destinations.CommunityNavDestination.COMMUNITY_ROUTE
import com.example.carrot.ui.destinations.HomeNavDestination.HOME_ROUTE
import com.example.carrot.ui.destinations.MyPageNavDestination.MYPAGE_ROUTE

@Composable
fun BottomNavBar(
    navController: NavController
) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf(
        HOME_ROUTE,
        COMMUNITY_ROUTE,
        CHAT_ROUTE,
        MYPAGE_ROUTE
    )
    val navIcons = listOf(
        Home.icon,
        Community.icon,
        Chat.icon,
        MyPage.icon
    )

    val selectedNavIcons = listOf(
        Home.selectedIcon,
        Community.selectedIcon,
        Chat.selectedIcon,
        MyPage.selectedIcon
    )

    NavigationBar(
        modifier = Modifier
            .drawColoredShadow(offsetX = 2.dp)
            .height(70.dp),
        containerColor = Color.White,
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    if (selectedItem == index){
                        Icon(selectedNavIcons[index], contentDescription = item)
                    } else {
                        Icon(navIcons[index], contentDescription = item)
                    }
                },
                label = {
                    Text(item)
                },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(item)
                    // TODO("페이지 전환 후 돌아 올 때 상태를 저장하게 바꿔야함.")
//                    {
//                        navController.graph.startDestinationRoute?.let { route ->
//                            popUpTo(route) {
//                                saveState = true
//                            }
//                        }
//                        launchSingleTop = true
//                        restoreState = true
//                    }
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    indicatorColor = Color.White
                ),
            )
        }
    }
}