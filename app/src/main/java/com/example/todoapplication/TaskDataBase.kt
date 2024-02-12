package com.example.todoapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "taskToDo")
data class TaskDataBase(

    @PrimaryKey(autoGenerate = true)
    var id : Int? = null,
    @ColumnInfo(name = "name")
    var title: String? = null,
    @ColumnInfo(name = "description")
    var description: String? = null,
    var date: Date? = null,
    var isDone : Boolean? = null

    )
