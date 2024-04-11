package com.phcannesson.aspectframelayoutdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.phcannesson.aspectframelayoutdemo.ui.HomeScreen
import com.phcannesson.aspectframelayoutdemo.ui.PlayerFixTextureViewScreen
import com.phcannesson.aspectframelayoutdemo.ui.PlayerKOScreen
import com.phcannesson.aspectframelayoutdemo.ui.theme.AspectFrameLayoutDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AspectFrameLayoutDemoTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                NavHost(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable("home") {
                        HomeScreen(
                            onClickBug = {
                                navController.navigate("player-bug") {
                                    popUpTo("home")
                                }
                            },
                            onClickFixTextureView = {
                                navController.navigate("player-ok-texture-view") {
                                    popUpTo("home")
                                }
                            })
                    }

                    composable("player-bug") {
                        PlayerKOScreen()
                    }

                    composable("player-ok-texture-view") {
                        PlayerFixTextureViewScreen()
                    }
                }
            }
        }
    }
}