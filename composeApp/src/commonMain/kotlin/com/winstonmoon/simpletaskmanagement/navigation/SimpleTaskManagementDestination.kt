package com.winstonmoon.simpletaskmanagement.navigation

sealed class SimpleTaskManagementDestination(val route: String) {
    data object TaskScreen : SimpleTaskManagementDestination("task")
    data object CalendarScreen : SimpleTaskManagementDestination("calendar")
    data object SettingScreen : SimpleTaskManagementDestination("setting")
}