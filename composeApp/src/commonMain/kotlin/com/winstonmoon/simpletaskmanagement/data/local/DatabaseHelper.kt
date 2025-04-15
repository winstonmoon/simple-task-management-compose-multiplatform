package com.winstonmoon.simpletaskmanagement.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.db.SqlDriver
import com.winstonmoon.simpletaskmanagement.cache.SimpleTaskManagementDatabase
import com.winstonmoon.simpletaskmanagement.cache.composeApp.newInstance
import com.winstonmoon.simpletaskmanagement.cache.Task
import com.winstonmoon.simpletaskmanagement.model.Priority
import com.winstonmoon.simpletaskmanagement.model.Status
import com.winstonmoon.simpletaskmanagement.sqldelight.transactionWithContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

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

    fun selectAllTasks(): List<com.winstonmoon.simpletaskmanagement.model.Task> = dbRef.simpleTaskManagementDatabaseQueries
        .selectAllTasks()
        .executeAsList()
        .map {
            it.toDomain()
        }
//        .asFlow()
//        .mapToList(Dispatchers.Default)
//        .flowOn(backgroundDispatcher)

    fun selectTaskByStatus(status: String): Flow<List<Task>> = dbRef.simpleTaskManagementDatabaseQueries
        .selectTaskByStatus(status = status)
        .asFlow()
        .mapToList(Dispatchers.Default)
        .flowOn(backgroundDispatcher)

    private fun Task.toDomain(): com.winstonmoon.simpletaskmanagement.model.Task = com.winstonmoon.simpletaskmanagement.model.Task(
        id = id,
        title = title,
        status = Status.valueOf(status),
        priority = Priority.valueOf(priority),
        dueDate = dueDate,
    )
}