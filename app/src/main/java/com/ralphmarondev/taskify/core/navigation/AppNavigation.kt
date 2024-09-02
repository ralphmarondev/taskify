package com.ralphmarondev.taskify.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ralphmarondev.taskify.core.model.Task
import com.ralphmarondev.taskify.features.home.HomeScreen
import com.ralphmarondev.taskify.features.newtask.NewTaskScreen
import com.ralphmarondev.taskify.features.notification.NotificationScreen
import com.ralphmarondev.taskify.features.settings.SettingScreen
import com.ralphmarondev.taskify.features.updatetask.UpdateTaskScreen
import kotlinx.serialization.Serializable

class Screens {
    @Serializable
    object Home

    @Serializable
    object NewTask

    @Serializable
    data class UpdateTask(
        val id: Int,
        val title: String,
        val description: String,
        val startTime: String,
        val endTime: String,
        val createDate: String
    )

    @Serializable
    object Settings

    @Serializable
    object Notification
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
        composable<Screens.UpdateTask> {
            val args = it.toRoute<Screens.UpdateTask>()
            UpdateTaskScreen(
                navController = navController,
                task = Task(
                    id = args.id,
                    title = args.title,
                    description = args.description,
                    startTime = args.startTime,
                    endTime = args.endTime,
                    createDate = args.createDate
                )
            )
        }
        composable<Screens.Settings> {
            SettingScreen(navController)
        }
        composable<Screens.Notification> {
            NotificationScreen(navController)
        }
    }
}