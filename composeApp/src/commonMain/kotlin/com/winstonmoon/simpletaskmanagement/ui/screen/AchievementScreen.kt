package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.winstonmoon.simpletaskmanagement.ui.component.CustomAppBar
import kotlinx.serialization.Serializable

@Serializable
data object AchievementRoute

@Composable
fun AchievementRoute(
    drawerState: DrawerState,
    modifier: Modifier = Modifier,
) {
    AchievementScreen(
        drawerState = drawerState,
        modifier = modifier,
    )
}

@Composable
internal fun AchievementScreen(
    drawerState: DrawerState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CustomAppBar(
                drawerState = drawerState,
                title = "Achievement"
            )
        }
    ) { paddingValues ->

    }
}