package com.winstonmoon.simpletaskmanagement.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.winstonmoon.simpletaskmanagement.database.dao.TaskDao
import com.winstonmoon.simpletaskmanagement.database.model.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}