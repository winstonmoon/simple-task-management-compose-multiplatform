package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Serializable
data object InputTaskRoute

@Composable
fun InputTaskRoute(
    modifier: Modifier = Modifier,
) {
    InputTaskScreen(
        modifier = modifier,
    )
}

@Composable
internal fun InputTaskScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Text("Hello World")
    }
}