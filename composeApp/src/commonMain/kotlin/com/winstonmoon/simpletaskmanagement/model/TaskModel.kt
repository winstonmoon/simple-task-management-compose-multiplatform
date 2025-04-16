package com.winstonmoon.simpletaskmanagement.model

enum class Status {
    READY,
    IN_PROGRESS,
    COMPLETED,
}

enum class Priority {
    HIGH,
    MEDIUM,
    LOW,
}

data class TaskModel(
    val id: Long,
    val title: String,
    val status: Status,
    val priority: Priority,
    val dueDate: Long?,
)
