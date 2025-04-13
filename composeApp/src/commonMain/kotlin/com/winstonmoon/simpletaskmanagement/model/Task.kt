package com.winstonmoon.simpletaskmanagement.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

enum class Progress {
    READY,
    IN_PROGRESS,
    COMPLETED,
}

enum class Priority {
    HIGH,
    MEDIUM,
    LOW,
}

data class Task(
    val id: Long,
    val title: String,
    val status: Progress,
    val priority: Priority,
    val dueDate: Long?,
)
