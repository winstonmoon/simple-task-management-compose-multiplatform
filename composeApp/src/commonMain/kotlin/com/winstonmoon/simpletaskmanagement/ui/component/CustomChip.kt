package com.winstonmoon.simpletaskmanagement.ui.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ChipElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.winstonmoon.simpletaskmanagement.ui.theme.SimpleTaskManagementTheme

@Composable
fun CustomAssistChipWithIcon(
    label: String,
    modifier: Modifier = Modifier,
) {
    AssistChip(
        modifier = modifier,
        onClick = {},
        label = {
            Text(text = label)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null,
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
            Text(text = label)
        },
        shape = RoundedCornerShape(24.dp),
    )
}