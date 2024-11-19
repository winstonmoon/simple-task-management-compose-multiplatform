package com.winstonmoon.simpletaskmanagement.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import simpletaskmanagement.composeapp.generated.resources.Res
import simpletaskmanagement.composeapp.generated.resources.ic_edit

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
                modifier = Modifier
                    .size(24.dp),
                painter = painterResource(Res.drawable.ic_edit),
                contentDescription = null,
                tint = Color.White
            )
        },
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
    )
}