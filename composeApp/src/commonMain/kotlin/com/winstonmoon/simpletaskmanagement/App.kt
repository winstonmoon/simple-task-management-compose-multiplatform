package com.winstonmoon.simpletaskmanagement

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.NetworkFetcher
import com.winstonmoon.simpletaskmanagement.ui.screen.AchievementRoute
import com.winstonmoon.simpletaskmanagement.ui.screen.SettingsRoute
import com.winstonmoon.simpletaskmanagement.ui.screen.TaskRoute
import com.winstonmoon.simpletaskmanagement.ui.theme.SimpleTaskManagementTheme
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import kotlin.reflect.KClass

@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview
fun App() {
    SimpleTaskManagementTheme {
        KoinContext {
            setSingletonImageLoaderFactory { context ->
                ImageLoader.Builder(context)
                    .components {
                        add(
                            NetworkFetcher.Factory(
                            networkClient = TODO(),
                            cacheStrategy = TODO()
                            )
                        )
                    }
                    .build()
            }

//            SimpleTaskManagementNavGraph()
            val navController = rememberNavController()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            ModalNavigationDrawer(
                drawerState = drawerState,
                gesturesEnabled = drawerState.isOpen,
                drawerContent = {
                    ModalDrawerSheet {
                        DrawerContent { route ->
                            scope.launch {
                                drawerState.close()
                            }
                            navController.navigate(route)
                        }
//                        NavigationDrawerItem(
//                            label = { Text("") },
//                            icon = { Icon(Icons.Default.Add, null) },
//                            selected = false,
//                            onClick = {
//                            },
//                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
//                        )
//                        NavigationDrawerItem(
//                            label = { Text("") },
//                            icon = { Icon(Icons.Default.Settings, null) },
//                            selected = false,
//                            onClick = {
//                            },
//                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
//                        )
//                        NavigationDrawerItem(
//                            label = { Text("") },
//                            icon = { Icon(Icons.Default.Settings, null) },
//                            selected = false,
//                            onClick = {
//                            },
//                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
//                        )
                    }
                }
            ) {
                NavHost(
                    startDestination = TaskRoute,
                    navController = navController,
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable<TaskRoute> {
                        TaskRoute(drawerState)
                    }
                    composable<AchievementRoute> {
                        AchievementRoute(drawerState)
                    }
                    composable<SettingsRoute> {
                        SettingsRoute()
                    }
                }
            }
        }
    }
}

@Composable
private fun DrawerContent(
    onMenuClick: (KClass<*>) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(150.dp),
                imageVector = Icons.Filled.AccountCircle,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        DrawerMenu.entries.forEach {
            NavigationDrawerItem(
                label = { Text(text = it.title) },
                icon = { Icon(imageVector = it.icon, contentDescription = null) },
                selected = false,
                onClick = {
                    onMenuClick(it.route)
                }
            )
        }
    }
}

enum class DrawerMenu(
    val icon: ImageVector,
    val title: String,
    val route: KClass<*>,
) {
    Task(
        icon = Icons.Filled.Add,
        title = "Task",
        route = TaskRoute::class
    ),
    Achievement(
        icon = Icons.Filled.ThumbUp,
        title = "Achievement",
        route = AchievementRoute::class
    ),
    Settings(
        icon = Icons.Filled.Settings,
        title = "Settings",
        route = SettingsRoute::class
    )
}