package com.ralphmarondev.taskify.core.application

import android.app.Application
import androidx.room.Room
import com.ralphmarondev.taskify.core.data.room_db.TaskDatabase

class TaskifyApp : Application() {
    companion object {
        lateinit var database: TaskDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            TaskDatabase::class.java,
            TaskDatabase.NAME
        ).build()
    }
}