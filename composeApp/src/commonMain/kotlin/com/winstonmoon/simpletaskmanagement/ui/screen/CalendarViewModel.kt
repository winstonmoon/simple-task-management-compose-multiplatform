package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.winstonmoon.simpletaskmanagement.database.datasource.TaskDataSource
import com.winstonmoon.simpletaskmanagement.database.datasource.TaskDataSourceImpl
import com.winstonmoon.simpletaskmanagement.database.model.TaskEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class CalendarViewModel(
    private val taskDataSource: TaskDataSourceImpl,
) : ViewModel() {

    val tasks: StateFlow<List<TaskEntity>> =
        taskDataSource.getTasksByDueDate(dueDate = 0L)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
}