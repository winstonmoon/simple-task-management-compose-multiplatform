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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.winstonmoon.simpletaskmanagement.database.model.TaskEntity
import com.winstonmoon.simpletaskmanagement.model.Priority
import com.winstonmoon.simpletaskmanagement.model.Status
import com.winstonmoon.simpletaskmanagement.ui.component.CustomAppBar
import com.winstonmoon.simpletaskmanagement.ui.component.CustomFloatingActionButton
import com.winstonmoon.simpletaskmanagement.ui.component.CustomListItem
import com.winstonmoon.simpletaskmanagement.ui.theme.DarkJungleGreen
import com.winstonmoon.simpletaskmanagement.ui.theme.RomanSilver
import com.winstonmoon.simpletaskmanagement.ui.theme.VampireBlack
import com.winstonmoon.simpletaskmanagement.ui.theme.White
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import simpletaskmanagement.composeapp.generated.resources.Res
import simpletaskmanagement.composeapp.generated.resources.calendar_screen_title
import simpletaskmanagement.composeapp.generated.resources.floating_action_button_label_new
import simpletaskmanagement.composeapp.generated.resources.ic_arrow_back_ios_18
import simpletaskmanagement.composeapp.generated.resources.ic_arrow_forward_ios_18

@Serializable
data object CalendarRoute

@Composable
fun CalendarRoute(
    drawerState: DrawerState,
    onClickAddButton: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CalendarViewModel = koinViewModel<CalendarViewModel>()
) {
    val tasks by viewModel.tasks.collectAsStateWithLifecycle()

    CalendarScreen(
        tasks = tasks,
        drawerState = drawerState,
        onClickDay = {
            viewModel.getTasksByDueDate(it)
        },
        onClickAddButton = onClickAddButton,
        onClickDelete = {
            viewModel.deleteTask(it)
        },
        modifier = modifier,
    )
}

@Composable
internal fun CalendarScreen(
    tasks: List<TaskEntity>,
    drawerState: DrawerState,
    onClickDay: (Long) -> Unit,
    onClickAddButton: () -> Unit,
    onClickDelete: (Long) -> Unit,
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
                onClick = onClickAddButton,
                title = Res.string.floating_action_button_label_new,
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize(),
            // TODO check
            verticalArrangement = Arrangement.spacedBy(22.dp)
        ) {
            Calendar(
                onClickDay = onClickDay,
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                items(
                    tasks
                ) {
                    CustomListItem(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        todo = it.title,
                        status = it.status,
                        priority = it.priority,
                        dueDate = it.dueDate,
                        onClickEdit = {},
                        onClickDuplicate = {

                        },
                        onClickDelete = {
                            onClickDelete(it.id)
                        },
                    )
                    Spacer(
                        modifier = Modifier
                            .height(18.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Calendar(
    onClickDay: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val localDensity = LocalDensity.current
    var itemWidthDp by remember { mutableStateOf(0.dp) }
    val pagerState = rememberPagerState(pageCount = { 30 })

    Column(
        modifier = modifier
            .fillMaxWidth()
            .onGloballyPositioned { layoutCoordinates ->
                itemWidthDp = with(localDensity) {
                    (layoutCoordinates.size.width.toDp() - 80.dp) / 7
                }
            }
            .background(color = DarkJungleGreen)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Icon(
                modifier = Modifier
                    .clickable {

                    },
                painter = painterResource(Res.drawable.ic_arrow_back_ios_18),
                contentDescription = null,
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
//                    text = stringResource(),
                    text = "December",
                    color = White,
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
//                    text = stringResource(status.label),
                    text = "2024",
                    color = RomanSilver,
                    style = MaterialTheme.typography.labelLarge,
                )
            }
            Icon(
                modifier = Modifier
                    .clickable {

                    },
                painter = painterResource(Res.drawable.ic_arrow_forward_ios_18),
                contentDescription = null,
            )
        }

        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fixed(itemWidthDp),
            pageSpacing = 10.dp,
        ) { page ->

            Card(
                onClick = {
                    onClickDay(page.toLong())
                },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(6.dp),
                colors = CardColors(
                    contentColor = White,
                    containerColor = VampireBlack,
                    disabledContentColor = Color.White,
                    disabledContainerColor = Color.White,
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "$page",
                        style = MaterialTheme.typography.labelLarge,
                    )
                    Text(
                        text = "$page",
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
            }
        }
    }
}
