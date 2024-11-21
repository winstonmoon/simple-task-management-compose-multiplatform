package com.winstonmoon.simpletaskmanagement.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import simpletaskmanagement.composeapp.generated.resources.Res
import simpletaskmanagement.composeapp.generated.resources.ic_check_box_18
import simpletaskmanagement.composeapp.generated.resources.ic_check_box_outline_blank_18
import simpletaskmanagement.composeapp.generated.resources.ic_emergency_home_18
import simpletaskmanagement.composeapp.generated.resources.ic_low_priority_18
import simpletaskmanagement.composeapp.generated.resources.ic_progress_activity_18
import simpletaskmanagement.composeapp.generated.resources.ic_vertical_align_center_18
import simpletaskmanagement.composeapp.generated.resources.priority_label_high
import simpletaskmanagement.composeapp.generated.resources.priority_label_low
import simpletaskmanagement.composeapp.generated.resources.priority_label_medium
import simpletaskmanagement.composeapp.generated.resources.status_label_done
import simpletaskmanagement.composeapp.generated.resources.status_label_inprogress
import simpletaskmanagement.composeapp.generated.resources.status_label_ready

enum class Status(
    val icon: DrawableResource,
    val label: StringResource,
) {
    READY(
        icon = Res.drawable.ic_check_box_outline_blank_18,
        label = Res.string.status_label_ready,
    ),
    IN_PROGRESS(
        icon = Res.drawable.ic_progress_activity_18,
        label = Res.string.status_label_inprogress,
    ),
    DONE(
        icon = Res.drawable.ic_check_box_18,
        label = Res.string.status_label_done,
    ),
}

enum class Priority(
    val icon: DrawableResource,
    val label: StringResource,
) {
    Low(
      icon = Res.drawable.ic_low_priority_18,
        label = Res.string.priority_label_low,
    ),
    Medium(
        icon = Res.drawable.ic_vertical_align_center_18,
        label = Res.string.priority_label_medium,
    ),
    High(
        icon = Res.drawable.ic_emergency_home_18,
        label = Res.string.priority_label_high,
    ),
}

@Composable
fun CustomAssistChipWithIcon(
    label: StringResource,
    icon: DrawableResource,
    modifier: Modifier = Modifier,
) {
    AssistChip(
        modifier = modifier,
        onClick = {},
        enabled = false,
        label = {
            Text(
                text = stringResource(label),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelMedium,
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(icon),
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
        enabled = false,
        label = {
            Text(
                text = label,
                color = Color.White,
                style = MaterialTheme.typography.labelMedium,
            )
        },
        shape = RoundedCornerShape(24.dp),
    )
}