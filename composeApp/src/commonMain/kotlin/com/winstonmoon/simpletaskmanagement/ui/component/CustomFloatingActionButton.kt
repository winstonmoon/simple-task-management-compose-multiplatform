package com.winstonmoon.simpletaskmanagement.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import simpletaskmanagement.composeapp.generated.resources.Res
import simpletaskmanagement.composeapp.generated.resources.floating_action_button_label
import simpletaskmanagement.composeapp.generated.resources.ic_edit_24

@Composable
fun CustomFloatingActionButton(
    // TODO
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ExtendedFloatingActionButton(
        modifier = modifier,
        text = {
            Text(
                text = stringResource(Res.string.floating_action_button_label),
                color = Color.White,
            )
        },
        icon = {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(Res.drawable.ic_edit_24),
                contentDescription = null,
                tint = Color.White
            )
        },
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
    )
}