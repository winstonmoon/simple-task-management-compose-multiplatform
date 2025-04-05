package com.winstonmoon.simpletaskmanagement.di

import com.winstonmoon.simpletaskmanagement.Context
import com.winstonmoon.simpletaskmanagement.data.local.AppDataStore
import com.winstonmoon.simpletaskmanagement.data.local.AppDataStoreManager
import com.winstonmoon.simpletaskmanagement.data.local.DatabaseHelper
import com.winstonmoon.simpletaskmanagement.repository.TaskRepository
import com.winstonmoon.simpletaskmanagement.ui.screen.AchievementViewModel
import com.winstonmoon.simpletaskmanagement.ui.screen.CalendarViewModel
import com.winstonmoon.simpletaskmanagement.ui.screen.InputTaskViewModel
import com.winstonmoon.simpletaskmanagement.ui.screen.SettingsViewModel
import com.winstonmoon.simpletaskmanagement.ui.screen.TaskViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

fun commonModule(context: Context) = module {
    single<AppDataStore> { AppDataStoreManager(context) }
    single {
        DatabaseHelper(
            get(),
            Dispatchers.Default,
        )
    }
    single {
        TaskRepository(get())
    }
    factory { AchievementViewModel() }
    factory { CalendarViewModel(get()) }
    factory { InputTaskViewModel(get()) }
    factory { SettingsViewModel() }
    factory { TaskViewModel(get()) }
}