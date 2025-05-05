package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.lifecycle.ViewModel
import com.winstonmoon.simpletaskmanagement.database.datasource.TaskDataSource
import com.winstonmoon.simpletaskmanagement.database.datasource.TaskDataSourceImpl

class CalendarViewModel(
    private val taskDataSource: TaskDataSourceImpl,
) : ViewModel() {
}