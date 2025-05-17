package com.winstonmoon.simpletaskmanagement.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.winstonmoon.simpletaskmanagement.database.dao.TaskDao
import com.winstonmoon.simpletaskmanagement.database.model.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}
