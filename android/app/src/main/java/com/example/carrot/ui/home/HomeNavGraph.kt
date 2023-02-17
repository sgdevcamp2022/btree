package com.example.carrot.ui.home

import android.util.Log
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.carrot.model.SampleData
import com.example.carrot.ui.destinations.HomeNavDestination
import com.example.carrot.ui.destinations.HomeNavDestination.HOME_ROUTE
import com.example.carrot.ui.destinations.HomeNavDestination.HOME_ROUTER
import com.example.carrot.ui.destinations.HomeNavDestination.POST_CREATE_ROUTE
import com.example.carrot.ui.destinations.HomeNavDestination.POST_ID
import com.example.carrot.ui.destinations.HomeNavDestination.POST_ROUTE
import com.example.carrot.ui.home.post.create.PostCreateScreen
import com.example.carrot.ui.home.post.detail.PostDetailScreen

fun NavGraphBuilder.homeNavGraph(
    navController: NavController,
    homeAction: HomeAction,
    toggleMainBottomBar: () -> Unit
){

    navigation(startDestination = HOME_ROUTE, route = HOME_ROUTER) {
        composable(HOME_ROUTE){
            HomeScreen(
                navigateToPost = homeAction.navigateToPost,
                navigateToPostCreate = homeAction.navigateToPostCreate
            )
        }
        composable(
            route = "$POST_ROUTE/{postId}",
            arguments = listOf(navArgument("postId"){type = NavType.LongType})
        ){ backStackEntry ->
            val postId = backStackEntry.arguments?.getLong("postId")!!
            PostDetailScreen(
                postId = postId,
                onBack = homeAction.upPress,
                toggleMainBottomBar = toggleMainBottomBar
            )
        }
        composable(POST_CREATE_ROUTE){
            PostCreateScreen(
                onCancel = homeAction.upPress,
                toggleMainBottomBar = toggleMainBottomBar
            )
        }
    }
}

// TODO: 인터페이스로 만들 것
class HomeAction(navController: NavController){
    val navigateToPost: (Long) -> Unit = { test ->
        navController.navigate("$POST_ROUTE/$test")
    }

    val navigateToPostCreate: () -> Unit = {
        navController.navigate(POST_CREATE_ROUTE)
    }

    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}