package com.winstonmoon.simpletaskmanagement.database.datasource

import com.winstonmoon.simpletaskmanagement.database.model.TaskEntity
import com.winstonmoon.simpletaskmanagement.model.Status
import kotlinx.coroutines.flow.Flow

interface TaskDataSource {
    fun getTasksByStatus(status: Status): Flow<List<TaskEntity>>
    suspend fun insertTask(taskEntity: TaskEntity)
    suspend fun deleteTask(id: Long)
}