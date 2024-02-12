package com.example.todoapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.util.Date

@Dao
interface TaskDao {

    @Insert
    fun insertTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Query("SELECT * FROM taskToDo")
    fun getTask():List<Task>

    @Query("SELECT * FROM taskToDo WHERE date = :date")
    fun getTaskByDate(date : Date) : List<Task>


}