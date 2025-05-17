package com.winstonmoon.simpletaskmanagement.di

import com.winstonmoon.simpletaskmanagement.database.AppDatabase
import com.winstonmoon.simpletaskmanagement.database.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single<AppDatabase> { getDatabaseBuilder() }
}