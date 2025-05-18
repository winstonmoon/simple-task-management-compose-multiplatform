package com.winstonmoon.simpletaskmanagement.database.datasource

import com.winstonmoon.simpletaskmanagement.database.AppDatabase
import com.winstonmoon.simpletaskmanagement.database.model.TaskEntity
import com.winstonmoon.simpletaskmanagement.model.Status
import kotlinx.coroutines.flow.Flow

class TaskDataSourceImpl(private val appDatabase: AppDatabase): TaskDataSource {
    override fun getTasksByStatus(status: Status) : Flow<List<TaskEntity>> = appDatabase.taskDao().getTasksByStatus(status = status)
    override fun getTasksByDueDate(dueDate: Long): Flow<List<TaskEntity>>  = appDatabase.taskDao().getTaskByDueDate(dueDate = dueDate)
    override suspend fun deleteTask(id: Long) = appDatabase.taskDao().deleteTask(id = id)
    override suspend fun insertTask(taskEntity: TaskEntity) = appDatabase.taskDao().insertTask(taskEntity = taskEntity)
}