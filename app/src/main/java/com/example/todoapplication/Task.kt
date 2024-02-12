package com.example.todoapplication

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.Date

@Entity(tableName = "taskToDo")
@TypeConverters(value = [DateTypeConverter::class])
data class Task(

    @PrimaryKey(autoGenerate = true)
    var id : Int? = null,
    var title: String? = null,
    var description: String? = null,
    var date: Date? = null,
    var isDone : Boolean? = null

    )
