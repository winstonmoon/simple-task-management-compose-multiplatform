package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.winstonmoon.simpletaskmanagement.cache.Task
import com.winstonmoon.simpletaskmanagement.repository.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class TaskViewModel(
    private val taskRepository: TaskRepository,
) : ViewModel() {

    val readyTasks: StateFlow<List<Task>> = taskRepository.getTasksByStatus(status = "READY")
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList<Task>(),
        )

    val inProgressTasks: StateFlow<List<Task>> = taskRepository.getTasksByStatus(status = "IN_PROGRESS")
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList<Task>(),
        )

    val doneTasks: StateFlow<List<Task>> = taskRepository.getTasksByStatus(status = "DONE")
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList<Task>(),
        )
}