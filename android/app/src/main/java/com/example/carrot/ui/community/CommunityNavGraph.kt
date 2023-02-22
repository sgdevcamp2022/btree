package com.example.carrot.ui.community

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.carrot.ui.community.post.create.PostCreateScreen
import com.example.carrot.ui.community.post.detail.PostDetailScreen
import com.example.carrot.ui.destinations.CommunityNavDestination
import com.example.carrot.ui.destinations.CommunityNavDestination.COMMUNITY_ROUTE
import com.example.carrot.ui.destinations.CommunityNavDestination.COMMUNITY_ROUTER
import com.example.carrot.ui.destinations.CommunityNavDestination.POST_CREATE_ROUTE
import com.example.carrot.ui.destinations.CommunityNavDestination.POST_ROUTE

fun NavGraphBuilder.communityNavGraph(
    navController: NavController,
    communityAction: CommunityAction,
    toggleMainBottomBar: () -> Unit
){

    navigation(startDestination = COMMUNITY_ROUTE, route = COMMUNITY_ROUTER) {
        composable(COMMUNITY_ROUTE){
            CommunityScreen(
                navigateToPost = communityAction.navigateToPost,
                navigateToPostCreate = communityAction.navigateToPostCreate
            )
        }
        composable(
            route = "${POST_ROUTE}/{postId}",
            arguments = listOf(navArgument("postId"){type = NavType.LongType})
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getLong("postId")!!
            PostDetailScreen(
                postId = postId,
                onBack = communityAction.upPress,
                toggleMainBottomBar = toggleMainBottomBar
            )
        }
        composable(POST_CREATE_ROUTE){
            PostCreateScreen(
                onCancel = communityAction.upPress,
                toggleMainBottomBar = toggleMainBottomBar
            )
        }
    }
}

class CommunityAction(navController: NavController) {
    val navigateToPost: (Long) -> Unit = { postId ->
        navController.navigate("${CommunityNavDestination.POST_ROUTE}/${postId}")
    }

    val navigateToPostCreate: () -> Unit = {
        navController.navigate(POST_CREATE_ROUTE)
    }

    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}
