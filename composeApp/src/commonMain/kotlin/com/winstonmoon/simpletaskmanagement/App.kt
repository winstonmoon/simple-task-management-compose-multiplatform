package com.winstonmoon.simpletaskmanagement

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            val navBottomBarController = rememberNavController()
            Scaffold(
                bottomBar = {
                    BottomNavigation(navController = navBottomBarController)
                }
            ) { innerPadding ->
                BoxWithConstraints(modifier = Modifier.padding(innerPadding)) {

                    FloatingActionButton(
                        onClick = {},
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(12.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = null,
                            )
                    }

                    NavHost(
                        startDestination = TaskRoute,
                        navController = navBottomBarController,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        composable<TaskRoute> {
                            TaskRoute()
                        }
                        composable<AchievementRoute> {
                            AchievementRoute()
                        }

                    }
                }
            }
        }
    }
//    MaterialTheme {
//        var showContent by remember { mutableStateOf(false) }
//        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//            Button(onClick = { showContent = !showContent }) {
//                Text("Click me!")
//            }
//            AnimatedVisibility(showContent) {
//                val greeting = remember { Greeting().greet() }
//                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//                    Image(painterResource(Res.drawable.compose_multiplatform), null)
//                    Text("Compose: $greeting")
//                }
//            }
//        }
//    }
}