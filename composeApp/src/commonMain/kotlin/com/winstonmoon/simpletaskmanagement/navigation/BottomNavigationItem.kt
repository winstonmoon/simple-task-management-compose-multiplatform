package com.winstonmoon.simpletaskmanagement.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector
import com.winstonmoon.simpletaskmanagement.ui.screen.AchievementRoute
import com.winstonmoon.simpletaskmanagement.ui.screen.TaskRoute
import kotlin.reflect.KClass

sealed class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val icon: ImageVector,
    val route: KClass<*>,
) {
    data object Task : BottomNavigationItem(
        title = "Task",
        selectedIcon = Icons.Outlined.ThumbUp,
        icon = Icons.Filled.ThumbUp,
        route = TaskRoute::class,
    )
    data object Achievement : BottomNavigationItem(
        title = "Achievement",
        selectedIcon = Icons.Outlined.Add,
        icon = Icons.Filled.Add,
        route = AchievementRoute::class,
        )
}