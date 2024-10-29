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
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.Edit
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
import coil3.annotation.ExperimentalCoilApi
import com.winstonmoon.simpletaskmanagement.di.appModule
import com.winstonmoon.simpletaskmanagement.ui.screen.AchievementRoute
import com.winstonmoon.simpletaskmanagement.ui.screen.InputTaskRoute
import com.winstonmoon.simpletaskmanagement.ui.screen.SettingsRoute
import com.winstonmoon.simpletaskmanagement.ui.screen.TaskRoute
import com.winstonmoon.simpletaskmanagement.ui.theme.SimpleTaskManagementTheme
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview
fun App(context: Context) {

    KoinApplication(
        application = {
            modules(appModule(context))
        }
    ) {
//        setSingletonImageLoaderFactory { context ->
//            ImageLoader.Builder(context)
//                .components {
//                    add(NetworkFetcher.Factory(
//                        networkClient = TODO(),
//                        cacheStrategy = TODO()
//                    ))
//                }
//                .build()
//        }

        SimpleTaskManagementTheme {
            val navController = rememberNavController()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            ModalNavigationDrawer(
                drawerContent = {
                    ModalDrawerSheet {
                        DrawerContent(
                            modifier = Modifier,
                            onMenuClick = { route ->
                                scope.launch { drawerState.close() }
                                when (route) {
                                    DrawerMenu.Task -> navController.navigate(TaskRoute)
                                    DrawerMenu.Achievement -> navController.navigate(AchievementRoute)
                                    DrawerMenu.Settings -> navController.navigate(SettingsRoute)
                                }
                            }
                        )
                    }
                },
                drawerState = drawerState,
                gesturesEnabled = drawerState.isOpen
            ) {
                NavHost(
                    startDestination = TaskRoute,
                    navController = navController,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    composable<TaskRoute> {
                        TaskRoute(
                            drawerState,
                            onClickAddButton = {
                                navController.navigate(InputTaskRoute)
                            },
                        )
                    }
                    composable<AchievementRoute> {
                        AchievementRoute(
                            drawerState,
                            onClickAddButton = {
                                navController.navigate(InputTaskRoute)
                            },
                        )
                    }
                    composable<SettingsRoute> {
                        SettingsRoute(
                            onClickBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                    composable<InputTaskRoute> {
                        InputTaskRoute(
                            onClickBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DrawerContent(
    onMenuClick: (DrawerMenu) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
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
                    onMenuClick(it)
                }
            )
        }
    }
}

enum class DrawerMenu(
    val icon: ImageVector,
    val title: String,
) {
    Task(
        icon = Icons.Outlined.Edit,
        title = "Task",
    ),
    Achievement(
        icon = Icons.Filled.ThumbUp,
        title = "Achievement",
    ),
    Settings(
        icon = Icons.Filled.Settings,
        title = "Settings",
    )
}