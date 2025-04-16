package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.winstonmoon.simpletaskmanagement.model.TaskModel
import com.winstonmoon.simpletaskmanagement.repository.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class TaskViewModel(
    private val taskRepository: TaskRepository,
) : ViewModel() {

    val readyTasks: StateFlow<List<TaskModel>> = taskRepository.getTasksByStatus(status = "READY")
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList(),
        )

    val inProgressTasks: StateFlow<List<TaskModel>> = taskRepository.getTasksByStatus(status = "IN_PROGRESS")
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList(),
        )

    val doneTasks: StateFlow<List<TaskModel>> = taskRepository.getTasksByStatus(status = "DONE")
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList(),
        )
}