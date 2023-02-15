package com.example.carrot.ui.myPage

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.carrot.ui.destinations.MyPageNavDestination

fun NavGraphBuilder.myPageNavGraph(navController: NavController){

    navigation(startDestination = MyPageNavDestination.MYPAGE_ROUTE, route = MyPageNavDestination.MYPAGE_ROUTER) {
        composable(MyPageNavDestination.MYPAGE_ROUTE){
            MyPageScreen()
        }
    }
}