package com.winstonmoon.simpletaskmanagement.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CustomFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ExtendedFloatingActionButton(
        modifier = modifier,
        text = {
            Text(
                text = "New",
                color = Color.White,
            )
        },
        icon = {
            Icon(
                // TODO: change icon
                imageVector = Icons.Filled.Add,
                contentDescription = null,
                tint = Color.White,
            )
        },
        onClick = onClick,
    )
}