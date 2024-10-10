package com.winstonmoon.simpletaskmanagement

import com.winstonmoon.simpletaskmanagement.navigation.SimpleTaskManagementNavGraph
import com.winstonmoon.simpletaskmanagement.ui.theme.SimpleTaskManagementTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import androidx.compose.runtime.Composable
import coil3.network.NetworkFetcher
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