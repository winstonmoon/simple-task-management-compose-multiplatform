package com.winstonmoon.simpletaskmanagement.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSHomeDirectory
import com.winstonmoon.simpletaskmanagement.database.AppDatabase
import com.winstonmoon.simpletaskmanagement.database.instantiateImpl

fun getDatabaseBuilder(): AppDatabase {
    val dbFilePath = "${NSHomeDirectory()}/simple-task-management.db"
    return Room.databaseBuilder<AppDatabase>(
        name = dbFilePath,
        factory = { AppDatabase::class.instantiateImpl() }
    ).setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}