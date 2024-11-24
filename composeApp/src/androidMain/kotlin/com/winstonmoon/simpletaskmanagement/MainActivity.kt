package com.winstonmoon.simpletaskmanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        startKoin {
//            androidLogger()
//            androidContext(applicationContext)
//            modules(
//                appModule,
//                module {
//                    single<Activity> { this@MainActivity }
//                }
//            )
//        }
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            App(application)
        }
    }
}