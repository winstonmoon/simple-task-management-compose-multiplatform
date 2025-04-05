package com.winstonmoon.simpletaskmanagement

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.winstonmoon.simpletaskmanagement.cache.SimpleTaskManagementDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            SimpleTaskManagementDatabase.Schema,
            get(),
            "SimpleTaskManagementDatabase"
        )
    }
}