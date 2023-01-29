package com.example.carrot.ui.chat

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.carrot.ui.destinations.ChatNavDestination

fun NavGraphBuilder.chatNavGraph(navController: NavController){

    navigation(startDestination = ChatNavDestination.CHAT_ROUTE, route = ChatNavDestination.CHAT_ROUTER) {
        composable(ChatNavDestination.CHAT_ROUTE){
            ChatScreen()
        }
    }
}