package com.example.carrot.ui.sign

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.carrot.AuthenticateViewModel
import com.example.carrot.ui.destinations.AuthNavDestination.ANNOUNCEMENT_SCREEN
import com.example.carrot.ui.destinations.AuthNavDestination.AUTH_ROUTER
import com.example.carrot.ui.destinations.AuthNavDestination.FIRST_ENTRANCE
import com.example.carrot.ui.destinations.AuthNavDestination.SIGNIN_SCREEN
import com.example.carrot.ui.destinations.AuthNavDestination.SIGNUP_SCREEN
import com.example.carrot.ui.destinations.HomeNavDestination.HOME_ROUTE
import com.example.carrot.ui.sign.firstEntrance.FirstEntranceScreen
import com.example.carrot.ui.sign.login.LogInScreen
import com.example.carrot.ui.sign.signup.SignUpScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    authAction: AuthAction,
    authenticateViewModel: AuthenticateViewModel
){
    navigation(startDestination = ANNOUNCEMENT_SCREEN, route = AUTH_ROUTER.toString()) {
        composable(ANNOUNCEMENT_SCREEN){
            AnnouncementScreen(
                navigateToLogin = authAction.navigateToLoginScreen,
                navigateToSignup = authAction.navigateToSignUpScreen
            )
        }
        composable(SIGNIN_SCREEN){
            LogInScreen(
                navigateToHome = authAction.navigateToHomeScreen,
                navigateToFirstEntranceScreen = authAction.navigateToFirstEntranceScreen,
                authenticateViewModel = authenticateViewModel,
                onBack = authAction.upPress
            )
        }
        composable(SIGNUP_SCREEN){
            SignUpScreen(
                navigateToLoginScreen = authAction.navigateToLoginScreen,
                onBack = authAction.upPress
            )
        }
        composable(FIRST_ENTRANCE){
            FirstEntranceScreen(
                navigateToHome = authAction.navigateToHomeScreen,
                authenticateViewModel = authenticateViewModel,
                onBack = authAction.upPress
            )
        }
    }
}

class AuthAction(navController: NavController){

    val navigateToLoginScreen: () -> Unit = {
        navController.navigate(SIGNIN_SCREEN)
    }
    val navigateToSignUpScreen: () -> Unit = {
        navController.navigate(SIGNUP_SCREEN)
    }
    val navigateToFirstEntranceScreen: () -> Unit = {
        navController.navigate(FIRST_ENTRANCE)
    }
    val navigateToHomeScreen: () -> Unit = {
        navController.navigate(HOME_ROUTE)
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}