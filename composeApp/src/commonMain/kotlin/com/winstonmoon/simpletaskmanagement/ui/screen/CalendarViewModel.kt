package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.lifecycle.ViewModel
import com.winstonmoon.simpletaskmanagement.repository.TaskRepository

class CalendarViewModel(
    private val taskRepository: TaskRepository,
) : ViewModel() {
}