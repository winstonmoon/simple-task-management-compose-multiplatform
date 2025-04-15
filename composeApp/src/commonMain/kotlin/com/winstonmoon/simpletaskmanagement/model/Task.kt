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

data class Task(
    val id: Long,
    val title: String,
    val status: Status,
    val priority: Priority,
    val dueDate: Long?,
)
