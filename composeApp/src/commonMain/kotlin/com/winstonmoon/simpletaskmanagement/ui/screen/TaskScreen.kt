package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.winstonmoon.simpletaskmanagement.ui.component.CustomAppBar
import kotlinx.serialization.Serializable

@Serializable
data object TaskRoute

@Composable
fun TaskRoute(
    drawerState: DrawerState,
    modifier: Modifier = Modifier,
) {
    TaskScreen(
        drawerState= drawerState,
        modifier = modifier,
    )
}

@Composable
internal fun TaskScreen(
    drawerState: DrawerState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CustomAppBar(
                drawerState = drawerState,
                title = "Task"
            )
        }
    ) { paddingValues ->
        BoxWithConstraints(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            FloatingActionButton(
                onClick = {
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(12.dp),
                ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null,
                )
            }
        }
    }
}