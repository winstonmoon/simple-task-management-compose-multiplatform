package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.winstonmoon.simpletaskmanagement.model.Task
import com.winstonmoon.simpletaskmanagement.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(
    private val taskRepository: TaskRepository,
) : ViewModel() {

    private val _readyTask = MutableStateFlow<List<Task>>(emptyList())
    val readyTask: StateFlow<List<Task>> = _readyTask

    init {
        viewModelScope.launch {
            taskRepository.getTasksByStatus(status = "READY").collectLatest {
                _readyTask.value = it
            }
        }
    }

    val readyTasks: StateFlow<List<Task>> = taskRepository.getTasksByStatus(status = "READY")
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList(),
        )

    val inProgressTasks: StateFlow<List<Task>> = taskRepository.getTasksByStatus(status = "IN_PROGRESS")
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList(),
        )

    val doneTasks: StateFlow<List<Task>> = taskRepository.getTasksByStatus(status = "DONE")
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList(),
        )

    fun deleteTask(id: Long) {
        viewModelScope.launch {
            taskRepository.deleteTask(id = id)
        }
    }
}