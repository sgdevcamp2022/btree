package com.example.carrot.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.carrot.Home
import com.example.carrot.model.SampleData
import com.example.carrot.ui.destinations.HomeNavDestination
import com.example.carrot.ui.destinations.HomeNavDestination.HOME_ROUTE
import com.example.carrot.ui.destinations.HomeNavDestination.HOME_ROUTER
import com.example.carrot.ui.destinations.HomeNavDestination.POST_ID
import com.example.carrot.ui.home.post.PostScreen

fun NavGraphBuilder.homeNavGraph(navController: NavController, homeAction: HomeAction){

    navigation(startDestination = HOME_ROUTE, route = HOME_ROUTER) {
        composable(HOME_ROUTE){
            HomeScreen(
                navigateToPost = homeAction.navigateToPost
            )
        }
        composable("${HomeNavDestination.POST_ROUTE}/{$POST_ID}"){ backStackEntry ->
            val postId = backStackEntry.arguments?.getLong(POST_ID)
            PostScreen(
                post = SampleData.sampleSalePost[postId!!.toInt()],
                onBack = homeAction.upPress
            )
        }
    }
}

// TODO: 인터페이스로 만들 것
class HomeAction(navController: NavController){
    val navigateToPost: (Long) -> Unit = { postId ->
        navController.navigate("${HomeNavDestination.POST_ROUTE}/${postId}")
    }

    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}