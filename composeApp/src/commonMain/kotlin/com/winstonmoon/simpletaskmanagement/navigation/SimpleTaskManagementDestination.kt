package com.winstonmoon.simpletaskmanagement.navigation

sealed class SimpleTaskManagementDestination(val route: String) {
    data object AchievementScreen : SimpleTaskManagementDestination("achievement")
    data object InputTaskScreen : SimpleTaskManagementDestination("input_task")
    data object SettingsScreen : SimpleTaskManagementDestination("settings")
    data object TaskScreen : SimpleTaskManagementDestination("task")
}