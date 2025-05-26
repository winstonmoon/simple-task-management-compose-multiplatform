package com.winstonmoon.simpletaskmanagement.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.winstonmoon.simpletaskmanagement.database.model.TaskEntity
import com.winstonmoon.simpletaskmanagement.ui.component.CustomAppBar
import com.winstonmoon.simpletaskmanagement.ui.component.CustomFloatingActionButton
import com.winstonmoon.simpletaskmanagement.ui.component.CustomListItem
import com.winstonmoon.simpletaskmanagement.ui.theme.DarkJungleGreen
import com.winstonmoon.simpletaskmanagement.ui.theme.RomanSilver
import com.winstonmoon.simpletaskmanagement.ui.theme.VampireBlack
import com.winstonmoon.simpletaskmanagement.ui.theme.White
import kotlinx.datetime.*
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import simpletaskmanagement.composeapp.generated.resources.Res
import simpletaskmanagement.composeapp.generated.resources.calendar_screen_title
import simpletaskmanagement.composeapp.generated.resources.floating_action_button_label_new
import simpletaskmanagement.composeapp.generated.resources.ic_arrow_back_ios_18
import simpletaskmanagement.composeapp.generated.resources.ic_arrow_drop_down_24
import simpletaskmanagement.composeapp.generated.resources.ic_arrow_forward_ios_18

@Serializable
data object CalendarRoute

const val CENTRAL_PAGE_INDEX = Int.MAX_VALUE / 2

@Composable
fun CalendarRoute(
    drawerState: DrawerState,
    onClickAddButton: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CalendarViewModel = koinViewModel<CalendarViewModel>()
) {
    viewModel.createDisplayedWeekDays()
    val tasks by viewModel.tasks.collectAsStateWithLifecycle()
    val displayedWeekdays by viewModel.displayedWeekDays.collectAsStateWithLifecycle()

    CalendarScreen(
        tasks = tasks,
        displayedWeekdays = displayedWeekdays,
        drawerState = drawerState,
        onClickDay = {
            viewModel.getTasksByDueDate(it.toEpochDays().toLong())
        },
        onClickAddButton = onClickAddButton,
        onClickDelete = {
            viewModel.deleteTask(it)
        },
        onPagerStateChanged = {
            viewModel.createDisplayedWeekDays()
        },
        initCalendar = {
            viewModel.createDisplayedWeekDays()
        },
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CalendarScreen(
    tasks: List<TaskEntity>,
    displayedWeekdays: List<LocalDate>,
    drawerState: DrawerState,
    onClickDay: (LocalDate) -> Unit,
    onClickAddButton: () -> Unit,
    onClickDelete: (Long) -> Unit,
    onPagerStateChanged: (Int) -> Unit,
    initCalendar: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LifecycleEventEffect(Lifecycle.Event.ON_CREATE) {
        initCalendar()
    }

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
            var shouldShowDatePicker by remember { mutableStateOf(false) }
            val datePickerState = rememberDatePickerState()
            Calendar(
                displayedWeekdays = displayedWeekdays,
                onClickDay = onClickDay,
                onClickYearMonth = {
                    shouldShowDatePicker = true
                },
                onPagerStateChanged = onPagerStateChanged,
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
            if (shouldShowDatePicker) {
                DatePickerDialog(
                    onDismissRequest = { shouldShowDatePicker = false },
//                onDismissRequest = onDismiss,
                    confirmButton = {
                        TextButton(onClick = {
//                            onDateSelected(datePickerState.selectedDateMillis)
                            shouldShowDatePicker = false
//                        onDismiss()
                        }) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {  shouldShowDatePicker = false }) {
//                    TextButton(onClick = onDismiss) {
                            Text("Cancel")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Calendar(
    displayedWeekdays: List<LocalDate>,
    onClickDay: (LocalDate) -> Unit,
    onClickYearMonth: (Boolean) -> Unit,
    onPagerStateChanged: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(
        initialPage = CENTRAL_PAGE_INDEX,
        pageCount = { Int.MAX_VALUE }
    )

    LaunchedEffect(pagerState) {
        onPagerStateChanged(pagerState.currentPage)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = DarkJungleGreen)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Icon(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable {

                    },
                painter = painterResource(Res.drawable.ic_arrow_back_ios_18),
                contentDescription = null,
            )
            Row (
                modifier = Modifier.clickable {
                    onClickYearMonth(true)
                },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
//                    text = stringResource(),
                        text = displayedWeekdays.first().month.toString().substring(0, 1).uppercase() +
                                displayedWeekdays.first().month.toString().substring(1, 3).lowercase(),
                        color = White,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Text(
//                    text = stringResource(status.label),
                        text = displayedWeekdays.first().year.toString(),
                        color = RomanSilver,
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
                Icon(
                    painter = painterResource(Res.drawable.ic_arrow_drop_down_24),
                    contentDescription = null,
                )
            }
            Icon(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable {

                    },
                painter = painterResource(Res.drawable.ic_arrow_forward_ios_18),
                contentDescription = null,
            )
        }

        HorizontalPager(
            state = pagerState,
            pageSpacing = 10.dp,
        ) { page ->
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (displayedWeekdays.isNotEmpty()) {
                    displayedWeekdays.forEach {
                        CardItem(
                            date = it,
                            onClickDay = onClickDay,
                        )
                    }
                } else {
                    repeat(7) {
                        Spacer(modifier = Modifier.weight(1f).padding(4.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun RowScope.CardItem(
    date: LocalDate,
    onClickDay: (LocalDate) -> Unit,
) {
    Card(
        onClick = {
            onClickDay(date)
        },
        modifier = Modifier
            .weight(1f)
            .padding(4.dp),
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors().copy(
            contentColor = White,
            containerColor = VampireBlack,
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                // TODO
                text = date.dayOfWeek.toString().substring(0, 1).uppercase() +
                        date.dayOfWeek.toString().substring(1, 3).lowercase(),
                style = MaterialTheme.typography.labelLarge,
            )
            Text(
                text = date.dayOfMonth.toString(),
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}
