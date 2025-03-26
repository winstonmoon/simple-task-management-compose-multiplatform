package com.winstonmoon.simpletaskmanagement.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Task(
    @SerialName("id")
    val id: Long,
    @SerialName("title")
    val title: String,
    @SerialName("status")
    val status: String,
    @SerialName("priority")
    val priority: String,
    @SerialName("dueDate")
    val dueDate: Long?,
)
