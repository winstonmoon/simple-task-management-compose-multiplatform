package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

@Serializable
data object TaskRoute

@Composable
fun TaskRoute(

) {
    TaskScreen()
}

@Composable
internal fun TaskScreen(

) {
    Box {
        Text("hello world")
    }
}