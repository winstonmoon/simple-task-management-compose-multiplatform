package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.winstonmoon.simpletaskmanagement.ui.component.CustomAppBar
import com.winstonmoon.simpletaskmanagement.ui.component.CustomAssistChip
import com.winstonmoon.simpletaskmanagement.ui.component.CustomFloatingActionButton
import com.winstonmoon.simpletaskmanagement.ui.component.CustomListItem
import com.winstonmoon.simpletaskmanagement.ui.component.Priority
import com.winstonmoon.simpletaskmanagement.ui.component.Status
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import simpletaskmanagement.composeapp.generated.resources.Res
import simpletaskmanagement.composeapp.generated.resources.ic_arrow_drop_down_24
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
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize(),
        ) {
            SectionTitle(
                status = Status.READY,
                number = 10,
                isExpended = true,
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(18.dp),
            ) {
                items(
                    listOf("WorkOut", "HomeWork", "Prepare Test", "WorkOut", "HomeWork", "Prepare Test", "WorkOut", "HomeWork", "Prepare Test")
                ) {
                    CustomListItem(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        todo = it,
                        status = Status.READY,
                        priority = Priority.Low,
                        onClickAdd = {},
                        onClickEdit = {},
                        onClickDuplicate = {},
                        onClickDelete = {},
                    )
                }
            }
            SectionTitle(
                status = Status.IN_PROGRESS,
                number = 10,
                isExpended = true,
            )
            SectionTitle(
                status = Status.DONE,
                number = 10,
                isExpended = true,
            )
        }
    }
}

@Composable
private fun SectionTitle(
    status: Status,
    number: Int,
    isExpended: Boolean,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .background(color = MaterialTheme.colorScheme.background),
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterStart),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = stringResource(status.label),
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
            )
            CustomAssistChip(
                modifier = Modifier,
                label = number.toString()
            )
        }
        Icon(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clickable {

                },
            painter = painterResource(Res.drawable.ic_arrow_drop_down_24),
            contentDescription = null,
        )
    }
}