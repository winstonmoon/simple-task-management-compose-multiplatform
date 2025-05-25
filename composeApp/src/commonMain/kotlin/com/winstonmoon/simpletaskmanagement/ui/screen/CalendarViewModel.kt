package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.winstonmoon.simpletaskmanagement.database.datasource.TaskDataSourceImpl
import com.winstonmoon.simpletaskmanagement.database.model.TaskEntity
import com.winstonmoon.simpletaskmanagement.util.getWeekDates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class CalendarViewModel(
    private val taskDataSource: TaskDataSourceImpl,
) : ViewModel() {

    private val _displayedWeekDays = MutableStateFlow<List<LocalDate>>(emptyList())
    val displayedWeekDays: StateFlow<List<LocalDate>> = _displayedWeekDays

    // TODO
    fun createDisplayedWeekDays() {
        _displayedWeekDays.value = getWeekDates()
    }

    private val _tasks = MutableStateFlow<List<TaskEntity>>(emptyList())
    val tasks: StateFlow<List<TaskEntity>> = _tasks

    fun getTasksByDueDate(dueDate: Long) {
        viewModelScope.launch {
            taskDataSource.getTasksByDueDate(dueDate = dueDate)
                .map {
                    _tasks.value = it
                }
        }
    }

    fun deleteTask(id: Long) {
        viewModelScope.launch {
            taskDataSource.deleteTask(id = id)
        }
    }
}