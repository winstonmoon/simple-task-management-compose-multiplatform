package com.winstonmoon.simpletaskmanagement.ui.component

import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CustomChip(
    modifier: Modifier = Modifier
) {
    AssistChip(
        onClick = {},
        label = { Text(text = "test") },
        modifier = modifier,
        enabled = true,
        leadingIcon = { },
        trailingIcon = TODO(),
        shape = TODO(),
        colors = TODO(),
        elevation = TODO(),
        border = TODO()
    )
}