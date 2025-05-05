package com.winstonmoon.simpletaskmanagement.model

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
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
    LOW(
        icon = Res.drawable.ic_low_priority_18,
        label = Res.string.priority_label_low,
    ),
    MEDIUM(
        icon = Res.drawable.ic_vertical_align_center_18,
        label = Res.string.priority_label_medium,
    ),
    HIGH(
        icon = Res.drawable.ic_emergency_home_18,
        label = Res.string.priority_label_high,
    ),
}

data class Task(
    val id: Long? = null,
    val title: String,
    val status: Status,
    val priority: Priority,
    val dueDate: Long?,
)