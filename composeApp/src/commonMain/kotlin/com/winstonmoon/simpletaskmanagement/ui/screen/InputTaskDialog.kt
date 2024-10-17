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
    InputTaskDialog(
        modifier = modifier,
    )
}

@Composable
internal fun InputTaskDialog(
    modifier: Modifier = Modifier,
) {

}