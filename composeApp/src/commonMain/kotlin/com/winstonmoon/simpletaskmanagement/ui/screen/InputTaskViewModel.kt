package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.winstonmoon.simpletaskmanagement.model.Priority
import com.winstonmoon.simpletaskmanagement.model.Status
import com.winstonmoon.simpletaskmanagement.model.TaskModel
import com.winstonmoon.simpletaskmanagement.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InputTaskViewModel(
    private val taskRepository: TaskRepository,
) : ViewModel() {

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title

    private val _status = MutableStateFlow("")
    val status: StateFlow<String> = _status

    private val _priority = MutableStateFlow("")
    val priority: StateFlow<String> = _priority

    private val _date = MutableStateFlow(0L)
    val date: StateFlow<Long> = _date

    fun setTitle(title: String) {
        _title.value = title
    }

    fun setStatus(status: String) {
        _status.value = status
    }

    fun setPriority(priority: String) {
        _priority.value = priority
    }

    fun setDate(date: Long?) {
        date?.let {
            _date.value = it
        }
    }

    fun updateTask() {
        viewModelScope.launch {
            taskRepository.insertTask(
                TaskModel(
                    id = 1,
                    title = title.value,
                    status = Status.valueOf(status.value),
                    priority = Priority.valueOf(priority.value),
                    dueDate = date.value,
                )
            )
        }
    }
}