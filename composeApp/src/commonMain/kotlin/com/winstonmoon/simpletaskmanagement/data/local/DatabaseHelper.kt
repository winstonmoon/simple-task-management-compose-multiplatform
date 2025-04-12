package com.winstonmoon.simpletaskmanagement.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.db.SqlDriver
import com.winstonmoon.simpletaskmanagement.cache.SimpleTaskManagementDatabase
import com.winstonmoon.simpletaskmanagement.cache.composeApp.newInstance
import com.winstonmoon.simpletaskmanagement.cache.Task
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

    fun selectAllTasks(): Flow<List<Task>> = dbRef.simpleTaskManagementDatabaseQueries
        .selectAllTasks()
        .asFlow()
        .mapToList(Dispatchers.Default)
        .flowOn(backgroundDispatcher)

    fun selectTaskByStatus(status: String): Flow<List<Task>> = dbRef.simpleTaskManagementDatabaseQueries
        .selectTaskByStatus(status = status)
        .asFlow()
        .mapToList(Dispatchers.Default)
        .flowOn(backgroundDispatcher)
}