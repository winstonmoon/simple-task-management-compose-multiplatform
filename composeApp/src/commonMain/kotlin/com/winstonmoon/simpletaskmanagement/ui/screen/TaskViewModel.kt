package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.lifecycle.ViewModel
import com.winstonmoon.simpletaskmanagement.repository.TaskRepository

class TaskViewModel(
    private val taskRepository: TaskRepository,
) : ViewModel() {

    val tasks = taskRepository.getTasks()
}