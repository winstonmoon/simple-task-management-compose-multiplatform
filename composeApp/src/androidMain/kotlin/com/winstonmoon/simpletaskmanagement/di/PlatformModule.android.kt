package com.winstonmoon.simpletaskmanagement.di

import androidx.room.RoomDatabase
import com.winstonmoon.simpletaskmanagement.database.AppDatabase
import com.winstonmoon.simpletaskmanagement.database.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

//actual fun platformModule(context: Context): Module = module {
//    single<SqlDriver> {
//        AndroidSqliteDriver(
//            SimpleTaskManagementDatabase.Schema,
//            context = context,
//            "SimpleTaskManagementDatabase"
//        )
//    }
//}

actual fun platformModule(): Module = module {
    single<AppDatabase> { getDatabaseBuilder(get()) }
}