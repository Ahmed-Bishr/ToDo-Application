package com.example.todoapplication

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Todos")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val date: Date? = null,
    var isDone: Boolean? = null,
)
