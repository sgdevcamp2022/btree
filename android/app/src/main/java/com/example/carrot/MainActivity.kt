package com.example.carrot

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.carrot.ui.component.BottomNavBar
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
fun CarrotApp() {
    val navController = rememberNavController()

    Scaffold(
        content = { innerPadding ->
            NavContent(
                innerPadding = innerPadding,
                navController = navController
            )
        },
        bottomBar = { BottomNavBar(navController = navController) },
        containerColor = androidx.compose.ui.graphics.Color.White,
        contentColor = androidx.compose.ui.graphics.Color.White
    )
}

@Composable
fun NavContent(
    innerPadding: PaddingValues,
    navController: NavHostController
) {
    CarrotNavGraph(navController = navController)
}