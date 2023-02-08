package com.example.carrot.ui.sign

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.carrot.AuthenticateViewModel
import com.example.carrot.CarrotApp
import com.example.carrot.ui.destinations.AuthNavDestination.ANNOUNCEMENT_SCREEN
import com.example.carrot.ui.destinations.AuthNavDestination.AUTH_ROUTER
import com.example.carrot.ui.destinations.AuthNavDestination.IN_APP
import com.example.carrot.ui.destinations.AuthNavDestination.SIGNIN_SCREEN
import com.example.carrot.ui.destinations.AuthNavDestination.SIGNUP_SCREEN
import com.example.carrot.ui.destinations.HomeNavDestination.HOME_ROUTE

fun NavGraphBuilder.authNavGraph(navController: NavHostController, authAction: AuthAction, authenticateViewModel: AuthenticateViewModel){
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
                authenticateViewModel = authenticateViewModel,
                onBack = authAction.upPress
            )
        }
        composable(SIGNUP_SCREEN){

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
    val navigateToHomeScreen: () -> Unit = {
        navController.navigate(HOME_ROUTE)
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}