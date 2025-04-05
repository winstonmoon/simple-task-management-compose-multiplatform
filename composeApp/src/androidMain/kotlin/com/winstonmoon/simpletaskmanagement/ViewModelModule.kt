package com.winstonmoon.simpletaskmanagement

import com.winstonmoon.simpletaskmanagement.ui.screen.AchievementViewModel
import com.winstonmoon.simpletaskmanagement.ui.screen.CalendarViewModel
import com.winstonmoon.simpletaskmanagement.ui.screen.InputTaskViewModel
import com.winstonmoon.simpletaskmanagement.ui.screen.SettingsViewModel
import com.winstonmoon.simpletaskmanagement.ui.screen.TaskViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

actual val viewModelModule = module {
    viewModelOf(::AchievementViewModel)
    viewModelOf(::CalendarViewModel)
    viewModelOf(::InputTaskViewModel)
    viewModelOf(::SettingsViewModel)
    viewModelOf(::TaskViewModel)
}