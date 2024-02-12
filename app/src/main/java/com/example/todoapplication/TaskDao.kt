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
    fun insertTask(taskDataBase: TaskDataBase)

    @Delete
    fun deleteTask(taskDataBase: TaskDataBase)

    @Update
    fun updateTask(taskDataBase: TaskDataBase)

    @Query("SELECT * FROM taskToDo")
    fun getTask():List<TaskDataBase>

    @Query("SELECT * FROM taskToDo WHERE date = :date")
    fun getTaskByDate(date : Date) : List<TaskDataBase>


}