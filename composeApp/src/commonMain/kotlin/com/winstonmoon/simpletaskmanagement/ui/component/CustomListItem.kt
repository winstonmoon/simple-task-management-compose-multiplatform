package com.winstonmoon.simpletaskmanagement.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import simpletaskmanagement.composeapp.generated.resources.Res
import simpletaskmanagement.composeapp.generated.resources.ic_calendar_month_18
import simpletaskmanagement.composeapp.generated.resources.ic_more_vert_24


@Composable
fun CustomListItem(
    todo: String,
    status: Status,
    priority: Priority,
//    dueDate: Date,
    onClickMore: () -> Unit,
    modifier: Modifier = Modifier,
) {
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
                // TODO change icon
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            onClickMore()
                        },
                    painter = painterResource(Res.drawable.ic_more_vert_24),
                    contentDescription = null,
                )
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
                // TODO change text
                Text(
//                    text = dueDate,
                    text = "22 oct 2024",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}
