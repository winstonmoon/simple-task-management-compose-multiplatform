package com.winstonmoon.simpletaskmanagement.di

import com.winstonmoon.simpletaskmanagement.database.datasource.TaskDataSourceImpl
import com.winstonmoon.simpletaskmanagement.database.datasource.TaskDataSource
import com.winstonmoon.simpletaskmanagement.ui.screen.AchievementViewModel
import com.winstonmoon.simpletaskmanagement.ui.screen.CalendarViewModel
import com.winstonmoon.simpletaskmanagement.ui.screen.InputTaskViewModel
import com.winstonmoon.simpletaskmanagement.ui.screen.SettingsViewModel
import com.winstonmoon.simpletaskmanagement.ui.screen.TaskViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

//fun commonModule(context: Context) = module {
//    single<AppDataStore> { AppDataStoreManager(context) }
//
//    single {
//        DatabaseHelper(
//            get(),
//            Dispatchers.Default,
//        )
//    }
//    single {
//        LegacyTaskRepository(get())
//    }
//    viewModel { AchievementViewModel() }
//    viewModel { CalendarViewModel(get()) }
//    viewModel { InputTaskViewModel(get()) }
//    viewModel { SettingsViewModel() }
//    viewModel { TaskViewModel(get()) }
//}

//expect fun platformModule(context: Context): Module
expect fun platformModule(): Module

fun initKoin(config: KoinAppDeclaration? = null) = startKoin {
    config?.invoke(this)
    modules(
        provideDataSourceModule,
        provideViewModelModule,
        platformModule(),
    )
}

val provideViewModelModule = module {
    viewModel { AchievementViewModel() }
    viewModel { CalendarViewModel(get()) }
    viewModel { InputTaskViewModel(get()) }
    viewModel { SettingsViewModel() }
    viewModel { TaskViewModel(get()) }
}

val provideDataSourceModule = module {
    singleOf(::TaskDataSourceImpl).bind(TaskDataSource::class)
}