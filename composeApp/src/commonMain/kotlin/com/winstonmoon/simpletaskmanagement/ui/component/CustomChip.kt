package com.winstonmoon.simpletaskmanagement.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomAssistChipWithIcon(
    label: String,
    modifier: Modifier = Modifier,
) {
    AssistChip(
        modifier = modifier,
        onClick = {},
        label = {
            Text(
                text = label,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelMedium,
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = MaterialTheme.colorScheme.primary,
            )
        },
        shape = RoundedCornerShape(24.dp),
    )
}

@Composable
fun CustomAssistChip(
    label: String,
    modifier: Modifier = Modifier,
) {
    AssistChip(
        modifier = modifier,
        onClick = {},
        label = {
            Text(
                text = label,
                color = Color.White,
                style = MaterialTheme.typography.labelMedium
            )

        },
        shape = RoundedCornerShape(24.dp),
    )
}