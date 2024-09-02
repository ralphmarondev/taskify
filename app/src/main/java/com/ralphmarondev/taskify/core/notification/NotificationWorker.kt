package com.ralphmarondev.taskify.core.notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ralphmarondev.taskify.MainActivity
import com.ralphmarondev.taskify.R
import com.ralphmarondev.taskify.core.application.TaskifyApp
import com.ralphmarondev.taskify.core.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NotificationWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        checkForUpcomingTasks()
        return Result.success()
    }

    private suspend fun checkForUpcomingTasks() {
        val dao = TaskifyApp.database.dao()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.getDefault())
        val currentTime = dateFormat.format(Date())

        val startingTasks = withContext(Dispatchers.IO) {
            dao.getTasksStartingSoon(currentTime)
        }
        val endingTasks = withContext(Dispatchers.IO) {
            dao.getTasksEndingSoon(currentTime)
        }

        // notify for each task
        startingTasks.forEach { task ->
            showNotification(task, "is about to start")
        }

        endingTasks.forEach { task ->
            showNotification(task, "is about to end")
        }
    }

    private fun showNotification(
        task: Task,
        message: String
    ) {
        val channelId = "taskify_notification_channel"
        val notificationId = task.id

        // notification channel for android 0 and above
        val name = "Taskify Notification"
        val descriptionText = "Channel for Task notification"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(
            channelId,
            name,
            importance
        ).apply {
            description = descriptionText
        }

        val notificationManager: NotificationManager = applicationContext.getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager
        notificationManager.createNotificationChannel(channel)
        // end


        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("navigateTo", "NotificationScreen")
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            applicationContext, 0,
            intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.icons8_note)
            .setContentTitle("Task Reminder")
            .setContentText("Your ${task.title} $message")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(applicationContext)) {
            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            notify(notificationId, builder.build())
        }
    }
}