package com.winstonmoon.simpletaskmanagement.repository

import com.winstonmoon.simpletaskmanagement.data.local.DatabaseHelper
import com.winstonmoon.simpletaskmanagement.model.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(
    private val dbHelper: DatabaseHelper,
) {
    suspend fun insertTask(
        task: Task
    ) {
        dbHelper.insertTask(
            title = task.title,
            status = task.status.name,
            priority = task.priority.name,
            dueDate = task.dueDate,
        )
    }

    suspend fun updateTask(
        task: Task
    ) {
        task.id?.let {
            dbHelper.updateTask(
                id = it,
                title = task.title,
                status = task.status.name,
                priority = task.priority.name,
                dueDate = task.dueDate,
            )
        }
    }

    suspend fun deleteTask(id: Long) {
        dbHelper.deleteTask(id = id)
    }

    fun getTasksByStatus(status: String): Flow<List<Task>> =
        dbHelper.selectTaskByStatus(status = status)
}