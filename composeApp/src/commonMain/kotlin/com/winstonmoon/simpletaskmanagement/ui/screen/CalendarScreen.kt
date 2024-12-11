package com.winstonmoon.simpletaskmanagement.ui.screen

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.winstonmoon.simpletaskmanagement.ui.component.CustomAppBar
import com.winstonmoon.simpletaskmanagement.ui.component.CustomFloatingActionButton
import com.winstonmoon.simpletaskmanagement.ui.component.CustomListItem
import com.winstonmoon.simpletaskmanagement.ui.component.Priority
import com.winstonmoon.simpletaskmanagement.ui.component.Status
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.painterResource
import simpletaskmanagement.composeapp.generated.resources.Res
import simpletaskmanagement.composeapp.generated.resources.calendar_screen_title
import simpletaskmanagement.composeapp.generated.resources.ic_arrow_back_ios_18
import simpletaskmanagement.composeapp.generated.resources.ic_arrow_forward_ios_18

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
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize(),
            // TODO check
            verticalArrangement = Arrangement.spacedBy(22.dp)
        ) {
            Calendar()
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                items(
                    listOf("WorkOut", "HomeWork")
                ) {
                    CustomListItem(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        todo = it,
                        status = Status.DONE,
                        priority = Priority.Low,
                        onClickAdd = {},
                        onClickEdit = {},
                        onClickDuplicate = {},
                        onClickDelete = {},
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

// TODO
@Composable
private fun Calendar(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
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
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
//                    text = stringResource(status.label),
                    text = "2024",
                    color = Color.White,
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
        LazyHorizontalGrid(
            rows = GridCells.Fixed(7),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            content = {
                items(listOf(Pair("Sun", "31"), Pair("Mon", "1"), Pair("Tue", "2"), Pair("Wed", "3"), Pair("Thu", "4"), Pair("Fri", "5"), Pair("Sat", "6"), Pair("Sun", "31"), Pair("Mon", "1"), Pair("Tue", "2"), Pair("Wed", "3"), Pair("Thu", "4"), Pair("Fri", "5"), Pair("Sat", "6"))) {
                    Card(
                        modifier = Modifier
                            .weight(1f),
                        shape = RoundedCornerShape(6.dp),
                    ) {
                        Column {
                            Text(
                                text = it.first,
                                color = Color.White,
                                style = MaterialTheme.typography.labelLarge,
                            )
                            Text(
                                text = it.second,
                                color = Color.White,
                                style = MaterialTheme.typography.labelLarge,
                            )
                        }
                    }
                }
            }
        )
    }
}