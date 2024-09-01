package com.ralphmarondev.taskify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ralphmarondev.taskify.features.home.HomeScreen
import com.ralphmarondev.taskify.ui.theme.TaskifyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            TaskifyTheme {
                HomeScreen()
            }
        }
    }
}