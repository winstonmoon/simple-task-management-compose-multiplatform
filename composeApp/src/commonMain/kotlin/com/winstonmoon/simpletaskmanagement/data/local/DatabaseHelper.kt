package com.winstonmoon.simpletaskmanagement.data.local

import app.cash.sqldelight.db.SqlDriver
import com.winstonmoon.simpletaskmanagement.cache.SimpleTaskManagementDatabase
import com.winstonmoon.simpletaskmanagement.cache.Task
import com.winstonmoon.simpletaskmanagement.cache.composeApp.newInstance
import com.winstonmoon.simpletaskmanagement.model.Priority
import com.winstonmoon.simpletaskmanagement.model.Status
import com.winstonmoon.simpletaskmanagement.model.TaskModel
import com.winstonmoon.simpletaskmanagement.sqldelight.transactionWithContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class DatabaseHelper(
    sqlDriver: SqlDriver,
    private val backgroundDispatcher: CoroutineDispatcher
) {
    private val dbRef: SimpleTaskManagementDatabase = SimpleTaskManagementDatabase::class.newInstance(sqlDriver)

    suspend fun insertTask(
        title: String,
        status: String,
        priority: String,
        dueDate: Long?,
    ) {
        dbRef.transactionWithContext(backgroundDispatcher) {
            dbRef.simpleTaskManagementDatabaseQueries.insertTask(
                title = title,
                status = status,
                priority = priority,
                dueDate = dueDate,
            )
        }
    }

    suspend fun updateTask(
        id:Long,
        title: String,
        status: String,
        priority: String,
        dueDate: Long?,
    ) {
        dbRef.transactionWithContext(backgroundDispatcher) {
            dbRef.simpleTaskManagementDatabaseQueries.updateTask(
                id = id,
                title = title,
                status = status,
                priority = priority,
                dueDate = dueDate
            )
        }
    }

    suspend fun deleteTask(id: Long) {
        dbRef.transactionWithContext(backgroundDispatcher) {
            dbRef.simpleTaskManagementDatabaseQueries.deleteTask(id = id)
        }
    }

    fun selectAllTasks(): Flow<List<TaskModel>> = dbRef.simpleTaskManagementDatabaseQueries
        .selectAllTasks()
        .executeAsList()
        .map {
            it.toDomain()
        }.asFlow()
//        .asFlow()
//        .mapToList(Dispatchers.Default)
//        .flowOn(backgroundDispatcher)

    fun selectTaskByStatus(status: String): Flow<List<TaskModel>> = dbRef.simpleTaskManagementDatabaseQueries
        .selectTaskByStatus(status = status)
        .executeAsList()
        .map {
            it.toDomain()
        }.asFlow()
//        .asFlow()
//        .mapToList(Dispatchers.Default)
//        .flowOn(backgroundDispatcher)

    private fun Task.toDomain(): TaskModel = TaskModel(
        id = id,
        title = title,
        status = Status.valueOf(status),
        priority = Priority.valueOf(priority),
        dueDate = dueDate,
    )

    private fun <T> List<T>.asFlow(): Flow<List<T>> = flowOf(this)
}