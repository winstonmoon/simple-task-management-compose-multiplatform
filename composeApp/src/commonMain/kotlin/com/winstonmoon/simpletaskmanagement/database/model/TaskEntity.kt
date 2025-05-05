package com.winstonmoon.simpletaskmanagement.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.winstonmoon.simpletaskmanagement.model.Priority
import com.winstonmoon.simpletaskmanagement.model.Status
import com.winstonmoon.simpletaskmanagement.model.Task

@Entity(tableName = "task_entity")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val status: Status,
    val priority: Priority,
    val dueDate: Long,
)

fun TaskEntity.toExternalModel() = Task(
    id = id,
    title = title,
    status = status,
    priority = priority,
    dueDate = dueDate,
)