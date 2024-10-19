package com.winstonmoon.simpletaskmanagement

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.NetworkFetcher
import com.winstonmoon.simpletaskmanagement.navigation.SimpleTaskManagementNavGraph
import com.winstonmoon.simpletaskmanagement.ui.component.BottomNavigation
import com.winstonmoon.simpletaskmanagement.ui.screen.AchievementRoute
import com.winstonmoon.simpletaskmanagement.ui.screen.TaskRoute
import com.winstonmoon.simpletaskmanagement.ui.theme.SimpleTaskManagementTheme
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

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

            SimpleTaskManagementNavGraph()
//            val navBottomBarController = rememberNavController()
//            Scaffold(
//                bottomBar = {
//                    BottomNavigation(navController = navBottomBarController)
//                }
//            ) { innerPadding ->
//                BoxWithConstraints(modifier = Modifier.padding(innerPadding)) {
//
//                    FloatingActionButton(
//                        onClick = {},
//                        modifier = Modifier
//                            .align(Alignment.BottomEnd)
//                            .padding(12.dp),
//                    ) {
//                        Icon(
//                            imageVector = Icons.Filled.Add,
//                            contentDescription = null,
//                            )
//                    }
//
//                    NavHost(
//                        startDestination = TaskRoute,
//                        navController = navBottomBarController,
//                        modifier = Modifier.fillMaxSize()
//                    ) {
//                        composable<TaskRoute> {
//                            TaskRoute()
//                        }
//                        composable<AchievementRoute> {
//                            AchievementRoute()
//                        }
//
//                    }
//                }
//            }
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            val navBottomBarController = rememberNavController()
            ModalNavigationDrawer(
                drawerState = drawerState,
                gesturesEnabled = drawerState.isOpen,
                drawerContent = {
                    ModalDrawerSheet {
                        NavigationDrawerItem(
                            label = { Text("") },
                            icon = { Icon(Icons.Default.Add, null) },
                            selected = false,
                            onClick = {
                            },
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                        )
                        NavigationDrawerItem(
                            label = { Text("") },
                            icon = { Icon(Icons.Default.Settings, null) },
                            selected = false,
                            onClick = {
                            },
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                        )
                    }
                }
//                content = {
//                    BoxWithConstraints {
//                        SearchBar(
//                            modifier = Modifier.align(Alignment.TopCenter),
//                            query = text,
//                            onQueryChange = { text = it },
//                            onSearch = { active = false },
//                            active = false,
//                            onActiveChange = {
//                                active = it
//                            },
//                            placeholder = { Text("Search here") },
//                            leadingIcon = {
//                                IconButton(onClick = {
//                                    scope.launch {
//                                        drawerState.apply {
//                                            if (isClosed) open() else close()
//                                        }
//                                    }
//                                }) {
//                                    Icon(Icons.Default.Menu, contentDescription = "menu icon")
//                                }
//                            },
//                            trailingIcon = {
//                                Icon(
//                                    Icons.Default.Search,
//                                    contentDescription = "search icon"
//                                )
//                            },
//                        ) {
//
//                        }
//                        FloatingActionButton(
//                            onClick = {},
//                            modifier = Modifier
//                                .align(Alignment.BottomEnd)
//                                .padding(12.dp),
//                        ) {
//                            Icon(
//                                imageVector = Icons.Filled.Add,
//                                contentDescription = null,
//                            )
//                        }
//
//                        NavHost(
//                            startDestination = TaskRoute,
//                            navController = navBottomBarController,
//                            modifier = Modifier.fillMaxSize()
//                        ) {
//                            composable<TaskRoute> {
//                                TaskRoute()
//                            }
//                            composable<AchievementRoute> {
//                                AchievementRoute()
//                            }
//                        }
//                    }
//                }
            ) {
                Scaffold(
                    floatingActionButton = {
                        ExtendedFloatingActionButton(
                            text = { Text("Show drawer") },
                            icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                            onClick = {
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }
                        )
                    }
                ) { contentPadding ->
                    // Screen content
                }
            }
        }
    }
}