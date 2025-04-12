package com.winstonmoon.simpletaskmanagement.repository

import com.winstonmoon.simpletaskmanagement.cache.Task
import com.winstonmoon.simpletaskmanagement.data.local.DatabaseHelper
import kotlinx.coroutines.flow.Flow

class TaskRepository(
    private val dbHelper: DatabaseHelper,
) {
    suspend fun insertTask(
        title: String,
        status: String,
        priority: String,
        dueDate: Long?,
    ) {
        dbHelper.insertTask(
            title = title,
            status = status,
            priority = priority,
            dueDate = dueDate,
        )
    }

    suspend fun updateTask(
        id:Long,
        title: String,
        status: String,
        priority: String,
        dueDate: Long?,
    ) {
        dbHelper.updateTask(
            id = id,
            title = title,
            status = status,
            priority = priority,
            dueDate = dueDate,
        )
    }

    suspend fun deleteTask(id: Long) {
        dbHelper.deleteTask(id = id)
    }

    fun getTasks(): Flow<List<Task>> = dbHelper.selectAllTasks()

    fun getTasksByStatus(status: String): Flow<List<Task>> =
        dbHelper.selectTaskByStatus(status = status)

}