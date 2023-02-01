package com.example.carrot.ui.chat

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.carrot.ui.destinations.ChatNavDestination
import com.example.carrot.ui.destinations.ChatNavDestination.CHATROOM_ID

fun NavGraphBuilder.chatNavGraph(navController: NavController, chatAction: ChatAction){

    navigation(startDestination = ChatNavDestination.CHAT_ROUTE, route = ChatNavDestination.CHAT_ROUTER) {
        composable(ChatNavDestination.CHAT_ROUTE){
            ChatScreen(
                navigateToChattingRoom = chatAction.navigateToPost
            )
        }
        composable("${ChatNavDestination.CHATROOM_ROUTE}/{$CHATROOM_ID}"){ backStackEntry ->
            val chatRoomId = backStackEntry.arguments?.getLong(CHATROOM_ID)

        }
    }
}

class ChatAction(navController: NavController){
    val navigateToPost: (Long) -> Unit = { chatRoomId ->
        navController.navigate("${ChatNavDestination.CHATROOM_ROUTE}/${chatRoomId}")
    }

    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}