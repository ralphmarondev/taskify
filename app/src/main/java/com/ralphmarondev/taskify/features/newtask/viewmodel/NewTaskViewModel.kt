package com.ralphmarondev.taskify.features.newtask.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.taskify.core.application.TaskifyApp
import com.ralphmarondev.taskify.core.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewTaskViewModel : ViewModel() {
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
    fun createNewTask(
        task: Task
    ) {
        try {
            Log.d("ROOM", "Saving $task")
            viewModelScope.launch(Dispatchers.IO) {
                dao.createNewTask(task)
            }
            Log.d("ROOM", "Saved successfully!")
        } catch (ex: Exception) {
            Log.d("ROOM", "Error [create]: ${ex.message}")
        }
    }
}