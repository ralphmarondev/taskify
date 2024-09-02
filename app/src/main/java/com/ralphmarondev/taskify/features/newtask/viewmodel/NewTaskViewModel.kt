package com.ralphmarondev.taskify.features.newtask.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

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
}