package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.winstonmoon.simpletaskmanagement.ui.component.CustomAppBar
import com.winstonmoon.simpletaskmanagement.ui.component.CustomAssistChip
import com.winstonmoon.simpletaskmanagement.ui.component.CustomFloatingActionButton
import com.winstonmoon.simpletaskmanagement.ui.component.CustomListItem
import com.winstonmoon.simpletaskmanagement.ui.component.Priority
import com.winstonmoon.simpletaskmanagement.ui.component.Status
import kotlinx.serialization.Serializable
import simpletaskmanagement.composeapp.generated.resources.Res
import simpletaskmanagement.composeapp.generated.resources.task_title

@Serializable
data object TaskRoute

@Composable
fun TaskRoute(
    drawerState: DrawerState,
    onClickAddButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TaskScreen(
        drawerState = drawerState,
        onClickAddButton = onClickAddButton,
        modifier = modifier,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun TaskScreen(
    drawerState: DrawerState,
    onClickAddButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CustomAppBar(
                drawerState = drawerState,
                title = Res.string.task_title,
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(18.dp),
            ) {
                stickyHeader {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.Gray),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        Text(
                            text = "Todo",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleLarge,
                        )
                        CustomAssistChip(
                            label = "10"
                        )
                    }
                }
                items(
                    listOf("WorkOut", "HomeWork", "Prepare Test", "WorkOut", "HomeWork", "Prepare Test", "WorkOut", "HomeWork", "Prepare Test")
                ) {
                    CustomListItem(
                        todo = it,
                        status = Status.READY,
                        priority = Priority.Low,
                        onClickMore = {},
                    )
                }
                stickyHeader {

                }
                items(listOf("WorkOut", "HomeWork", "Prepare Test", "WorkOut", "HomeWork", "Prepare Test", "WorkOut", "HomeWork", "Prepare Test")) {

                }
                stickyHeader {

                }
                items(listOf("WorkOut", "HomeWork", "Prepare Test", "WorkOut", "HomeWork", "Prepare Test", "WorkOut", "HomeWork", "Prepare Test")) {

                }
            }
        }
    }
}

@Composable
private fun Title(
    status: Status,
    number: Int,
    isExpended: Boolean,
) {

}