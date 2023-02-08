package com.example.carrot

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowInsets.Type
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelStore
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.carrot.ui.component.BottomNavBar
import com.example.carrot.ui.destinations.AuthNavDestination
import com.example.carrot.ui.destinations.AuthNavDestination.AUTH_ROUTER
import com.example.carrot.ui.destinations.HomeNavDestination.HOME_ROUTER
import com.example.carrot.ui.theme.CarrotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarrotTheme {
                Surface {
                    CarrotApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CarrotApp(
    authenticateViewModel: AuthenticateViewModel = AuthenticateViewModel()
) {
    val navController = rememberNavController()

    Scaffold(
        content = { innerPadding ->
            NavContent(
                innerPadding = innerPadding,
                navController = navController,
                authenticateViewModel = authenticateViewModel
            )
        },
        bottomBar = {
            if(authenticateViewModel.authenticated.value){
                BottomNavBar(navController = navController)
            }
        },
        containerColor = androidx.compose.ui.graphics.Color.White,
        contentColor = androidx.compose.ui.graphics.Color.White
    )
}


@Composable
fun NavContent(
    innerPadding: PaddingValues,
    navController: NavHostController,
    authenticateViewModel: AuthenticateViewModel
) {
    CarrotNavGraph(
        navController = navController,
        authenticateViewModel = authenticateViewModel
    )
}