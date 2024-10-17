package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Serializable
data object AchievementRoute

@Composable
fun AchievementRoute(
    modifier: Modifier = Modifier,
) {
    AchievementScreen(
        modifier = modifier,
    )
}

@Composable
internal fun AchievementScreen(
    modifier: Modifier = Modifier,
) {

}