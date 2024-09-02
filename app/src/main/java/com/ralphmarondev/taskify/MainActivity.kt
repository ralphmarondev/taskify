package com.ralphmarondev.taskify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.ralphmarondev.taskify.core.navigation.AppNavigation
import com.ralphmarondev.taskify.core.navigation.Screens
import com.ralphmarondev.taskify.core.notification.NotificationWorker
import com.ralphmarondev.taskify.ui.theme.TaskifyTheme
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()

        val workRequest = PeriodicWorkRequestBuilder<NotificationWorker>(
            15, TimeUnit.MINUTES // minimum interval for periodic work
        ).build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            "TaskNotificationWork",
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )

        setContent {
            val navController = rememberNavController()
            val navigateTo = intent.getStringExtra("navigateTo")

            TaskifyTheme {
                AppNavigation(navController)

                if (navigateTo == "NotificationScreen") {
                    navController.navigate(Screens.Notification)
                }
            }
        }
    }
}