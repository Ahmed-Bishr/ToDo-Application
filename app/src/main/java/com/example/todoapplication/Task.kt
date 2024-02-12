package com.example.todoapplication

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "taskToDo")
data class Task(

    @PrimaryKey(autoGenerate = true)
    var id : Int? = null,
    var title: String? = null,
    var description: String? = null,
    var date: Date? = null,
    var isDone : Boolean? = null

    )
