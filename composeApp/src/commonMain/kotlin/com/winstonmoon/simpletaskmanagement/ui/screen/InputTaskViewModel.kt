package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.winstonmoon.simpletaskmanagement.database.datasource.TaskDataSourceImpl
import com.winstonmoon.simpletaskmanagement.database.model.TaskEntity
import com.winstonmoon.simpletaskmanagement.model.Priority
import com.winstonmoon.simpletaskmanagement.model.Status
import com.winstonmoon.simpletaskmanagement.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InputTaskViewModel(
    private val taskDataSource: TaskDataSourceImpl,
) : ViewModel() {

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title

    private val _status = MutableStateFlow(Status.READY)
    val status: StateFlow<Status> = _status

    private val _priority = MutableStateFlow(Priority.LOW)
    val priority: StateFlow<Priority> = _priority

    private val _date = MutableStateFlow(0L)
    val date: StateFlow<Long> = _date

    fun setTitle(title: String) {
        _title.value = title
    }

    fun setStatus(status: Status) {
        _status.value = status
    }

    fun setPriority(priority: Priority) {
        _priority.value = priority
    }

    fun setDate(date: Long?) {
        date?.let {
            _date.value = it
        }
    }

    fun insertTask() {
        viewModelScope.launch {
            taskDataSource.insertTask(
                TaskEntity(
                    title = title.value,
                    status = status.value,
                    priority = priority.value,
                    dueDate = date.value,
                )
            )
        }
    }
}