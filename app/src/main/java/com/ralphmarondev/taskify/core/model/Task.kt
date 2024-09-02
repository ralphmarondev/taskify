package com.ralphmarondev.taskify.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val startTime: String,
    val endTime: String,
    val createDate: String = createDate()
)

fun createDate(): String {
    val currentDateTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a")
    val createDate = currentDateTime.format(formatter)

    return createDate
}