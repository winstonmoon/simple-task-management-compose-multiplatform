package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.winstonmoon.simpletaskmanagement.ui.component.CustomAppBar
import kotlinx.serialization.Serializable
import simpletaskmanagement.composeapp.generated.resources.Res
import simpletaskmanagement.composeapp.generated.resources.achievement_screen_title

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
        modifier = modifier,
        topBar = {
            CustomAppBar(
                drawerState = drawerState,
                title = Res.string.achievement_screen_title,
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
        }
    }
}