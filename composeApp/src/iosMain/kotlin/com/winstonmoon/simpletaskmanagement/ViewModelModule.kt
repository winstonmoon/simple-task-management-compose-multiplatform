package com.winstonmoon.simpletaskmanagement

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val viewModelModule = module {
    singleOf(::AchievementViewModel)
    singleOf(::CalendarViewModel)
    singleOf(::InputTaskViewModel)
    singleOf(::SettingsViewModel)
    singleOf(::TaskViewModel)
}