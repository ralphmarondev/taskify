package com.ralphmarondev.taskify.core.data.room_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ralphmarondev.taskify.core.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    companion object {
        const val NAME = "tasks"
    }

    abstract fun dao(): TaskDao
}