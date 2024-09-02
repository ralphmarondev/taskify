package com.ralphmarondev.taskify.features.updatetask.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.taskify.core.application.TaskifyApp
import com.ralphmarondev.taskify.core.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UpdateTaskViewModel : ViewModel() {
    private val _title = MutableStateFlow("")
    val title: StateFlow<String> get() = _title

    fun setTitle(value: String) {
        _title.value = value
    }

    private val _description = MutableStateFlow("")
    val description: StateFlow<String> get() = _description

    fun setDescription(value: String) {
        _description.value = value
    }

    private val _startTime = MutableStateFlow("")
    val startTime: StateFlow<String> get() = _startTime

    fun setStartTime(value: String) {
        _startTime.value = value
    }

    private val _endTime = MutableStateFlow("")
    val endTime: StateFlow<String> get() = _endTime

    fun setEndTime(value: String) {
        _endTime.value = value
    }

    // CRUD OPERATION
    private val dao = TaskifyApp.database.dao()
    fun updateTask(
        task: Task
    ) {
        try {
            Log.d("ROOM", "Updating: $task")
            viewModelScope.launch(Dispatchers.IO) {
                dao.updateTask(task)
            }
            Log.d("ROOM", "Updated successfully!")
        } catch (ex: Exception) {
            Log.d("ROOM", "Error [update]: ${ex.message}")
        }
    }

    fun deleteTask(id: Int) {
        try {
            Log.d("ROOM", "Deleting: $id")
            viewModelScope.launch(Dispatchers.IO) {
                dao.deleteTask(id)
            }
            Log.d("ROOM", "Deleted successfully!")
        } catch (ex: Exception) {
            Log.d("ROOM", "Error [delete]: ${ex.message}")
        }
    }
}