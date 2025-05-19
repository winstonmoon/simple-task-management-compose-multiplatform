package com.winstonmoon.simpletaskmanagement.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.winstonmoon.simpletaskmanagement.model.Priority
import com.winstonmoon.simpletaskmanagement.model.Status
import com.winstonmoon.simpletaskmanagement.ui.theme.RomanSilver
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import simpletaskmanagement.composeapp.generated.resources.Res
import simpletaskmanagement.composeapp.generated.resources.dropdown_menu_delete
import simpletaskmanagement.composeapp.generated.resources.dropdown_menu_duplicate
import simpletaskmanagement.composeapp.generated.resources.dropdown_menu_edit
import simpletaskmanagement.composeapp.generated.resources.ic_calendar_month_18
import simpletaskmanagement.composeapp.generated.resources.ic_more_vert_24


@Composable
fun CustomListItem(
    todo: String,
    status: Status,
    priority: Priority,
    dueDate: Long,
    onClickEdit: () -> Unit,
    onClickDuplicate: () -> Unit,
    onClickDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var isDropDownMenuExpanded by remember { mutableStateOf(false) }
    val formattedDate = remember(dueDate) {
        val instant = Instant.fromEpochMilliseconds(dueDate)
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        val year = localDateTime.year.toString().padStart(4, '0')
        val month = localDateTime.month.toString().substring(0, 1).uppercase() +
                localDateTime.month.toString().substring(1, 3).lowercase()
        val day = localDateTime.dayOfMonth.toString().padStart(2, '0')
        "$day $month $year"
    }

    Card(
        modifier = modifier
            .fillMaxSize(),
        shape = RoundedCornerShape(4.dp),
        // TODO change color
        border = BorderStroke(width = 1.dp, color = Color.White),
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = todo,
                    style = MaterialTheme.typography.bodyLarge,
                )
                Box {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { isDropDownMenuExpanded = true },
                        painter = painterResource(Res.drawable.ic_more_vert_24),
                        contentDescription = null,
                    )
                    DropdownMenu(
                        expanded = isDropDownMenuExpanded,
                        onDismissRequest = { isDropDownMenuExpanded = false },
                    ) {
                        // TODO
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = stringResource(Res.string.dropdown_menu_edit),
                                )
                            },
                            onClick = onClickEdit
                        )
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = stringResource(Res.string.dropdown_menu_duplicate)
                                )
                            },
                            onClick = onClickDuplicate
                        )
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = stringResource(Res.string.dropdown_menu_delete)
                                )
                            },
                            onClick = onClickDelete
                        )
                    }
                }
            }
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                CustomAssistChipWithIcon(
                    label = status.label,
                    icon = status.icon,
                )
                CustomAssistChipWithIcon(
                    label = priority.label,
                    icon = priority.icon,
                )
            }
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Icon(
                    modifier = Modifier
                        .size(18.dp),
                    painter = painterResource(Res.drawable.ic_calendar_month_18),
                    contentDescription = null,
                )
                Text(
                    text = formattedDate,
                    color = RomanSilver,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}
