package com.winstonmoon.simpletaskmanagement

import android.app.Application
import org.koin.core.component.KoinComponent
import com.winstonmoon.simpletaskmanagement.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class SimpleTaskManagementApplication: Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@SimpleTaskManagementApplication)
        }
    }
}