package com.ralphmarondev.taskify.features.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ralphmarondev.taskify.core.application.TaskifyApp
import com.ralphmarondev.taskify.core.model.Task

class HomeViewModel: ViewModel() {
    private val dao = TaskifyApp.database.dao()

    val tasks: LiveData<List<Task>> = dao.getAllTasks()
}