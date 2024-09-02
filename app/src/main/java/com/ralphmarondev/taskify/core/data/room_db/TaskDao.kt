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
}