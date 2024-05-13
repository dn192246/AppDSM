package com.example.crud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.crud.ui.theme.CRUDTheme
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CRUDTheme {
                SplashScreenLauncher();
            }
        }
    }
}

@Composable
fun SplashScreenLauncher(){
    var showSplashScreen by remember { mutableStateOf(true) }
    LaunchedEffect(key1 = true) {
        delay(3000)
        showSplashScreen = false;
    }

    if(showSplashScreen){
        AnimatedVisibility(
            visible = showSplashScreen,
            enter = fadeIn(initialAlpha = 0.3f),
            exit = fadeOut(animationSpec = tween(durationMillis = 1000))
        ) {
            SplashScreen()
        }
    }
    else{
        AppNavigator()
    }


}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main_screen"){
        composable("main_screen") {
            MainScreen(navController)
        }
        composable("login_screen") {
            LoginScreen(navController)
        }
        composable("dashboard_screen"){
            DashboardScreen(navController)
        }
        composable("dateinfo_screen"){
            DateInfoScreen(navController)
        }
    }
}

