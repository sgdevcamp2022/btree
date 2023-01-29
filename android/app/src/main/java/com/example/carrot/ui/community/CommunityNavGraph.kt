package com.example.carrot.ui.community

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.carrot.ui.destinations.CommunityNavDestination

fun NavGraphBuilder.communityNavGraph(navController: NavController){

    navigation(startDestination = CommunityNavDestination.COMMUNITY_ROUTE, route = CommunityNavDestination.COMMUNITY_ROUTER) {
        composable(CommunityNavDestination.COMMUNITY_ROUTE){
            CommunityScreen()
        }
    }
}