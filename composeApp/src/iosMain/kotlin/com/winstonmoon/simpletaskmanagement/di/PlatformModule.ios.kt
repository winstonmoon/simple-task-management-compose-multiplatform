package com.winstonmoon.simpletaskmanagement.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.winstonmoon.simpletaskmanagement.cache.SimpleTaskManagementDatabase
import org.koin.dsl.module

actual val platformModule = module {
    single<SqlDriver> { NativeSqliteDriver(SimpleTaskManagementDatabase.Schema, "SimpleTaskManagementDatabase") }
}