package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.winstonmoon.simpletaskmanagement.database.datasource.TaskDataSourceImpl
import com.winstonmoon.simpletaskmanagement.database.model.TaskEntity
import com.winstonmoon.simpletaskmanagement.model.Status
import com.winstonmoon.simpletaskmanagement.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(
    private val taskDataSource: TaskDataSourceImpl,
) : ViewModel() {

    val readyTasks: StateFlow<List<TaskEntity>> =
        taskDataSource.getTasksByStatus(status = Status.READY)
            .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList(),
        )

    val inProgressTasks: StateFlow<List<TaskEntity>> =
        taskDataSource.getTasksByStatus(status = Status.IN_PROGRESS)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList(),
        )

    val doneTasks: StateFlow<List<TaskEntity>> =
        taskDataSource.getTasksByStatus(status = Status.DONE)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList(),
        )

    fun deleteTask(id: Long) {
        viewModelScope.launch {
            taskDataSource.deleteTask(id = id)
        }
    }
}