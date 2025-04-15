package com.winstonmoon.simpletaskmanagement.repository

import com.winstonmoon.simpletaskmanagement.cache.Task
import com.winstonmoon.simpletaskmanagement.data.local.DatabaseHelper
import com.winstonmoon.simpletaskmanagement.model.Priority
import com.winstonmoon.simpletaskmanagement.model.Status
import kotlinx.coroutines.flow.Flow

class TaskRepository(
    private val dbHelper: DatabaseHelper,
) {
    suspend fun insertTask(
        task: com.winstonmoon.simpletaskmanagement.model.Task
    ) {
        dbHelper.insertTask(
            title = task.title,
            status = task.status.name,
            priority = task.priority.name,
            dueDate = task.dueDate,
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

    private fun Task.toDomain(): com.winstonmoon.simpletaskmanagement.model.Task = com.winstonmoon.simpletaskmanagement.model.Task(
        id = id,
        title = title,
        status = Status.valueOf(status),
        priority = Priority.valueOf(priority),
        dueDate = dueDate,
    )
}