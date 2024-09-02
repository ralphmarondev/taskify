package com.ralphmarondev.taskify.core.data.room_db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.ralphmarondev.taskify.core.model.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    fun getAllTasks(): LiveData<List<Task>>

    @Upsert
    fun createNewTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Query("DELETE FROM Task WHERE id=:id")
    fun deleteTask(id: Int)

    // for notification
    @Query("SELECT * FROM Task WHERE datetime(startTime) <= datetime(:currentTime) AND datetime(startTime) >= datetime(:currentTime, '-15 minutes')")
    suspend fun getTasksStartingSoon(currentTime: String): List<Task>

    @Query("SELECT * FROM Task WHERE datetime(endTime) <= datetime(:currentTime) AND datetime(endTime) >= datetime(:currentTime, '-15 minutes')")
    suspend fun getTasksEndingSoon(currentTime: String): List<Task>
}