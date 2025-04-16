package com.winstonmoon.simpletaskmanagement.repository

import com.winstonmoon.simpletaskmanagement.data.local.DatabaseHelper
import com.winstonmoon.simpletaskmanagement.model.TaskModel
import kotlinx.coroutines.flow.Flow

class TaskRepository(
    private val dbHelper: DatabaseHelper,
) {
    suspend fun insertTask(
        taskModel: TaskModel
    ) {
        dbHelper.insertTask(
            title = taskModel.title,
            status = taskModel.status.name,
            priority = taskModel.priority.name,
            dueDate = taskModel.dueDate,
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

    fun getTasks(): Flow<List<TaskModel>> = dbHelper.selectAllTasks()

    fun getTasksByStatus(status: String): Flow<List<TaskModel>> =
        dbHelper.selectTaskByStatus(status = status)
}