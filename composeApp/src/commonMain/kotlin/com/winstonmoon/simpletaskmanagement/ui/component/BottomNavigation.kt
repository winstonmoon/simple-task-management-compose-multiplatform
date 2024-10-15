package com.winstonmoon.simpletaskmanagement.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import com.winstonmoon.simpletaskmanagement.navigation.BottomNavigationItem

@Composable
fun BottomNavigation(
    navController: NavController,
) {
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navController.currentDestination?.hasRoute()

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.background,
        tonalElevation = 8.dp
    ) {
        val items = listOf(
            BottomNavigationItem.Task,
            BottomNavigationItem.Achievement,
        )
        items.forEach { item ->
            navController.currentDestination?.hasRoute(item.route)?.let {
                NavigationBarItem(
                    label = {
                        Text(
                            text = item.title,
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = if (it) item.selectedIcon else item.icon,
                            contentDescription = null,
                        )
                    },
                    selected = it,
                    onClick = {

                    }
                )
            }
        }
    }
}