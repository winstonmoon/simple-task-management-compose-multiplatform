package com.winstonmoon.simpletaskmanagement.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.winstonmoon.simpletaskmanagement.cache.SimpleTaskManagementDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            SimpleTaskManagementDatabase.Schema,
            context = get(),
            "SimpleTaskManagementDatabase"
        )
    }
}