package com.winstonmoon.simpletaskmanagement

import androidx.compose.ui.window.ComposeUIViewController
import com.winstonmoon.simpletaskmanagement.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}