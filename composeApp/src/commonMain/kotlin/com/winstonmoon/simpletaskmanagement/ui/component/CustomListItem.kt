package com.winstonmoon.simpletaskmanagement.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CustomListItem(
    todo: String,
    onClickMore: () -> Unit,
    modifier: Modifier = Modifier,
    
) {
    Card(
        modifier = modifier
            .wrapContentSize()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        shape = RoundedCornerShape(4.dp),
        // TODO change color
        border = BorderStroke(width = 1.dp, color = Color.White)
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.SpaceBetween
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
                    imageVector = Icons.Filled.Add,
                    contentDescription = null,
                )
            }
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                // TODO CustomChip
            }
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                // TODO change icon
                Icon(
                    modifier = Modifier
                        .size(18.dp),
                    imageVector = Icons.Filled.Add,
                    contentDescription = null,
                )
                Text(
                    text = "",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}
