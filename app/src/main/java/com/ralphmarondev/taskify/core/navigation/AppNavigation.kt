package com.ralphmarondev.taskify.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ralphmarondev.taskify.features.home.HomeScreen
import com.ralphmarondev.taskify.features.newtask.NewTaskScreen
import com.ralphmarondev.taskify.features.settings.SettingScreen
import kotlinx.serialization.Serializable

class Screens {
    @Serializable
    object Home

    @Serializable
    object NewTask

    @Serializable
    object Settings
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home
    ) {
        composable<Screens.Home> {
            HomeScreen(navController)
        }
        composable<Screens.NewTask> {
            NewTaskScreen(navController)
        }
        composable<Screens.Settings> {
            SettingScreen(navController)
        }
    }
}