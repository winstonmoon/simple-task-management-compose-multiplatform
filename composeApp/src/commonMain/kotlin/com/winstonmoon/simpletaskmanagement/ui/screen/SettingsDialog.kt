package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Serializable
data object SettingsRoute

@Composable
fun SettingsRoute(
    modifier: Modifier = Modifier,
) {
    SettingsDialog(
        modifier = modifier,
    )
}

@Composable
internal fun SettingsDialog(
    modifier: Modifier = Modifier,
) {

}