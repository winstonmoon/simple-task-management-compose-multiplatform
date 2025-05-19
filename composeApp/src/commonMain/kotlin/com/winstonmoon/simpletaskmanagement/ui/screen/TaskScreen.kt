package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.winstonmoon.simpletaskmanagement.database.model.TaskEntity
import com.winstonmoon.simpletaskmanagement.model.Status
import com.winstonmoon.simpletaskmanagement.model.Task
import com.winstonmoon.simpletaskmanagement.ui.component.CustomAppBar
import com.winstonmoon.simpletaskmanagement.ui.component.CustomAssistChip
import com.winstonmoon.simpletaskmanagement.ui.component.CustomFloatingActionButton
import com.winstonmoon.simpletaskmanagement.ui.component.CustomListItem
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import simpletaskmanagement.composeapp.generated.resources.Res
import simpletaskmanagement.composeapp.generated.resources.floating_action_button_label_new
import simpletaskmanagement.composeapp.generated.resources.ic_arrow_drop_down_24
import simpletaskmanagement.composeapp.generated.resources.ic_list_24
import simpletaskmanagement.composeapp.generated.resources.ic_swap_vert_18
import simpletaskmanagement.composeapp.generated.resources.task_screen_title

@Serializable
data object TaskRoute

@Composable
fun TaskRoute(
    drawerState: DrawerState,
    onClickAddButton: () -> Unit,
    onClickDuplicate: (TaskEntity) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TaskViewModel = koinViewModel<TaskViewModel>(),
) {
    val readyTasks by viewModel.readyTasks.collectAsStateWithLifecycle()
    val inProgressTasks by viewModel.inProgressTasks.collectAsStateWithLifecycle()
    val doneTasks by viewModel.doneTasks.collectAsStateWithLifecycle()

    TaskScreen(
        readyTasks = readyTasks,
        inProgressTasks = inProgressTasks,
        doneTasks = doneTasks,
        drawerState = drawerState,
        onClickAddButton = onClickAddButton,
        onClickDuplicate = {
            onClickDuplicate(it)
        },
        onClickDelete = {
            viewModel.deleteTask(it)
        },
        modifier = modifier,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun TaskScreen(
    readyTasks: List<TaskEntity>,
    inProgressTasks: List<TaskEntity>,
    doneTasks: List<TaskEntity>,
    drawerState: DrawerState,
    onClickAddButton: () -> Unit,
    onClickDuplicate: (TaskEntity) -> Unit,
    onClickDelete: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CustomAppBar(
                drawerState = drawerState,
                title = Res.string.task_screen_title,
            )
        },
        floatingActionButton = {
            CustomFloatingActionButton(
                modifier = Modifier,
                onClick = onClickAddButton,
                title = Res.string.floating_action_button_label_new,
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize(),
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
            ) {
                stickyHeader {
                    TitleSection(
                        status = Status.READY,
                        number = readyTasks.size,
                        isExpended = true,
                    )
                }
                item {
                    SortSection()
                }
                items(
                    readyTasks
                ) {
                    if (readyTasks.isEmpty()) {
                        Text("No Task")
                    } else {
                        Spacer(
                            modifier = Modifier.height(18.dp)
                        )
                        CustomListItem(
                            modifier = Modifier
                                .padding(horizontal = 16.dp),
                            todo = it.title,
                            status = it.status,
                            priority = it.priority,
                            dueDate = it.dueDate,
                            onClickEdit = {},
                            onClickDuplicate = {
                                onClickDuplicate(it)
                            },
                            onClickDelete = {
                                onClickDelete(it.id)
                            },
                        )
                    }
                }
                stickyHeader {
                    TitleSection(
                        status = Status.IN_PROGRESS,
                        number = inProgressTasks.size,
                        isExpended = true,
                    )
                }
                item {
                    SortSection()
                }
                items(
                    inProgressTasks
                ) {
                    if (inProgressTasks.isEmpty()) {
                        Text("No Task")
                    } else {
                        Spacer(
                            modifier = Modifier.height(18.dp)
                        )
                        CustomListItem(
                            modifier = Modifier
                                .padding(horizontal = 16.dp),
                            todo = it.title,
                            status = it.status,
                            priority = it.priority,
                            dueDate = it.dueDate,
                            onClickEdit = {},
                            onClickDuplicate = {
                                onClickDuplicate(it)
                            },
                            onClickDelete = {
                                onClickDelete(it.id)
                            },
                        )
                    }
                }
                stickyHeader {
                    TitleSection(
                        status = Status.DONE,
                        number = doneTasks.size,
                        isExpended = true,
                    )
                }
                items(
                    doneTasks
                ) {
                    if (doneTasks.isEmpty()) {
                        Text("No Task")
                    } else {
                        CustomListItem(
                            modifier = Modifier
                                .padding(horizontal = 16.dp),
                            todo = it.title,
                            status = it.status,
                            priority = it.priority,
                            dueDate = it.dueDate,
                            onClickEdit = {},
                            onClickDuplicate = {
                                onClickDuplicate(it)
                            },
                            onClickDelete = {
                                onClickDelete(it.id)
                            },
                        )
                        Spacer(
                            modifier = Modifier.height(18.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TitleSection(
    status: Status,
    number: Int,
    isExpended: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(status.label),
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
            )
            CustomAssistChip(
                modifier = Modifier,
                label = number.toString()
            )
        }
        Icon(
            modifier = Modifier
                .padding(end = 16.dp)
                .clickable {

                },
            painter = painterResource(Res.drawable.ic_arrow_drop_down_24),
            contentDescription = null,
        )
    }
}

@Composable
private fun SortSection(

    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier
                    .size(18.dp),
                painter = painterResource(Res.drawable.ic_swap_vert_18),
                contentDescription = null,
            )
            Text(
                // TODO use parameter
                text = "Priority",
                // TODO change color
                color = Color.White,
                style = MaterialTheme.typography.labelLarge,
            )
        }
        Icon(
            modifier = Modifier
                .padding(end = 16.dp)
                .clickable {

                },
            painter = painterResource(Res.drawable.ic_list_24),
            contentDescription = null,
        )
    }
}