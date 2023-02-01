package com.example.carrot.ui.community

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.carrot.model.SampleData
import com.example.carrot.ui.community.post.PostScreen
import com.example.carrot.ui.destinations.CommunityNavDestination
import com.example.carrot.ui.destinations.CommunityNavDestination.POST_ID

fun NavGraphBuilder.communityNavGraph(navController: NavController, communityAction: CommunityAction){

    navigation(startDestination = CommunityNavDestination.COMMUNITY_ROUTE, route = CommunityNavDestination.COMMUNITY_ROUTER) {
        composable(CommunityNavDestination.COMMUNITY_ROUTE){
            CommunityScreen(
                navigateToPost = communityAction.navigateToPost
            )
        }
        composable("${CommunityNavDestination.POST_ROUTE}/{$POST_ID}") { backStackEntry ->
            val postId = backStackEntry.arguments?.getLong(POST_ID)
            PostScreen(
                post = SampleData.sampleComPost[postId!!.toInt()],
                onBack = communityAction.upPress
            )
        }
    }
}

class CommunityAction(navController: NavController) {
    val navigateToPost: (Long) -> Unit = { postId ->
        navController.navigate("${CommunityNavDestination.POST_ROUTE}/${postId}")
    }

    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}
