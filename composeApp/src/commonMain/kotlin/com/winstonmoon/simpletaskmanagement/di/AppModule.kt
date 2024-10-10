package com.winstonmoon.simpletaskmanagement.di

import com.winstonmoon.simpletaskmanagement.ui.screen.AchievementViewModel
import com.winstonmoon.simpletaskmanagement.ui.screen.InputTaskViewModel
import com.winstonmoon.simpletaskmanagement.ui.screen.SettingsViewModel
import com.winstonmoon.simpletaskmanagement.ui.screen.TaskViewModel
import org.koin.dsl.module

val appModule = module {
    factory { AchievementViewModel() }
    factory { InputTaskViewModel() }
    factory { SettingsViewModel() }
    factory { TaskViewModel() }
}