package com.winstonmoon.simpletaskmanagement.ui.screen

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

}