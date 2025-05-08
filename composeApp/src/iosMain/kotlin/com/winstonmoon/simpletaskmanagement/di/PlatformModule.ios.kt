package com.winstonmoon.simpletaskmanagement.di

import androidx.room.RoomDatabase
import com.winstonmoon.simpletaskmanagement.database.AppDatabase
import com.winstonmoon.simpletaskmanagement.database.getDatabaseBuilder
import org.koin.dsl.module


//actual fun platformModule(context: Context) = module {
//    single<SqlDriver> { NativeSqliteDriver(SimpleTaskManagementDatabase.Schema, "SimpleTaskManagementDatabase") }
//}

actual fun platformModule() = module {
    single<AppDatabase> { getDatabaseBuilder() }
}