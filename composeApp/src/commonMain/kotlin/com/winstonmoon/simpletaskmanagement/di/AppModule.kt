package com.winstonmoon.simpletaskmanagement.di

import com.winstonmoon.simpletaskmanagement.Context
import com.winstonmoon.simpletaskmanagement.data.local.AppDataStore
import com.winstonmoon.simpletaskmanagement.data.local.AppDataStoreManager
import com.winstonmoon.simpletaskmanagement.ui.screen.AchievementViewModel
import com.winstonmoon.simpletaskmanagement.ui.screen.InputTaskViewModel
import com.winstonmoon.simpletaskmanagement.ui.screen.SettingsViewModel
import com.winstonmoon.simpletaskmanagement.ui.screen.TaskViewModel
import org.koin.dsl.module

fun appModule(context: Context) = module {
    single<AppDataStore> { AppDataStoreManager(context) }
    factory { AchievementViewModel() }
    factory { InputTaskViewModel() }
    factory { SettingsViewModel() }
    factory { TaskViewModel() }
}