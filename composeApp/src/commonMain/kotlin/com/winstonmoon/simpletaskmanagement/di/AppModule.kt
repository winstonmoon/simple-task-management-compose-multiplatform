package com.winstonmoon.simpletaskmanagement.di

import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {

}

fun initializeKoin() {
    startKoin {
        modules(appModule)
    }
}