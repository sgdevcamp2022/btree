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
import com.example.carrot.ui.destinations.AuthNavDestination.ANNOUNCEMENT_SCREEN
import com.example.carrot.ui.destinations.AuthNavDestination.AUTH_ROUTER
import com.example.carrot.ui.destinations.HomeNavDestination.HOME_ROUTER
import com.example.carrot.ui.home.HomeAction
import com.example.carrot.ui.home.homeNavGraph
import com.example.carrot.ui.myPage.myPageNavGraph
import com.example.carrot.ui.sign.AuthAction
import com.example.carrot.ui.sign.authNavGraph

@Composable
fun CarrotNavGraph(navController: NavHostController = rememberNavController(), authenticateViewModel: AuthenticateViewModel) {

    val homeAction = remember(navController){ HomeAction(navController) }
    val communityAction = remember(navController){ CommunityAction(navController) }
    val chatAction = remember(navController){ ChatAction(navController)}
    val authAction = remember(navController){AuthAction(navController)}

    NavHost(navController = navController, startDestination = AUTH_ROUTER.toString() ){
        homeNavGraph(navController = navController, homeAction = homeAction)
        communityNavGraph(navController = navController, communityAction = communityAction)
        chatNavGraph(navController = navController, chatAction = chatAction)
        myPageNavGraph(navController = navController)
        // TODO: ADD NOTIFICATION ROUTE, LOCATION ROUTE
        authNavGraph(navController = navController, authAction = authAction, authenticateViewModel = authenticateViewModel)
    }
}

