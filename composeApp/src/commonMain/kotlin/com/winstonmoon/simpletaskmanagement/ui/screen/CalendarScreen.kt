package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.winstonmoon.simpletaskmanagement.ui.component.CustomAppBar
import com.winstonmoon.simpletaskmanagement.ui.component.CustomFloatingActionButton
import kotlinx.serialization.Serializable
import simpletaskmanagement.composeapp.generated.resources.Res
import simpletaskmanagement.composeapp.generated.resources.calendar_screen_title

@Serializable
data object CalendarRoute

@Composable
fun CalendarRoute(
    drawerState: DrawerState,
    onClickAddButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CalendarScreen(
        drawerState = drawerState,
        onClickAddButton = onClickAddButton,
        modifier = modifier,
    )
}

@Composable
internal fun CalendarScreen(
    drawerState: DrawerState,
    onClickAddButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CustomAppBar(
                drawerState = drawerState,
                title = Res.string.calendar_screen_title,
            )
        },
        floatingActionButton = {
            CustomFloatingActionButton(
                modifier = Modifier,
                onClick = onClickAddButton
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 12.dp,
                )
                .padding(paddingValues = paddingValues)
                .fillMaxSize(),
        ) {

        }
    }
}