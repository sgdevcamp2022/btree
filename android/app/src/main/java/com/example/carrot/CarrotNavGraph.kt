package com.example.carrot

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carrot.ui.chat.ChatAction
import com.example.carrot.ui.chat.chatNavGraph
import com.example.carrot.ui.community.CommunityAction
import com.example.carrot.ui.community.communityNavGraph
import com.example.carrot.ui.destinations.HomeNavDestination.HOME_ROUTER
import com.example.carrot.ui.home.HomeAction
import com.example.carrot.ui.home.homeNavGraph
import com.example.carrot.ui.myPage.myPageNavGraph

@Composable
fun CarrotNavGraph(navController: NavHostController = rememberNavController()) {

    val homeAction = remember(navController){ HomeAction(navController) }
    val communityAction = remember(navController){ CommunityAction(navController) }
    val chatAction = remember(navController){ ChatAction(navController)}

    NavHost(navController = navController, startDestination = HOME_ROUTER ){
        homeNavGraph(navController = navController, homeAction = homeAction)
        communityNavGraph(navController = navController, communityAction = communityAction)
        chatNavGraph(navController = navController, chatAction = chatAction)
        myPageNavGraph(navController = navController)
        // TODO: ADD NOTIFICATION ROUTE, LOCATION ROUTE
    }
}

