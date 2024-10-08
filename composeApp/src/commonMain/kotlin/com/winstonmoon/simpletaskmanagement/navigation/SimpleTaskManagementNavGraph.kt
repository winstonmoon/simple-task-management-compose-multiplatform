package com.winstonmoon.simpletaskmanagement.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.winstonmoon.simpletaskmanagement.ui.screen.TaskRoute

@Composable
fun SimpleTaskManagementNavGraph(
    modifier: Modifier = Modifier,
    startDestination: TaskRoute = TaskRoute,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        startDestination = startDestination,
        navController = navController,
    ) {
        composable<TaskRoute> {

        }

    }
}