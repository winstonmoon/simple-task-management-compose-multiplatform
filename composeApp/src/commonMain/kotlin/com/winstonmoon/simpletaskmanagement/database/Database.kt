package com.winstonmoon.simpletaskmanagement.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.winstonmoon.simpletaskmanagement.database.dao.TaskDao
import com.winstonmoon.simpletaskmanagement.database.model.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase(), DB {
    abstract fun taskDao(): TaskDao
    override fun clearAllTables(): Unit {}
}

interface DB {
    fun clearAllTables(): Unit {}
}
