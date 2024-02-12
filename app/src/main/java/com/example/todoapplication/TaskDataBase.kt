package com.example.todoapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Task::class], version = 1)
@TypeConverters(value = [DateTypeConverter::class])
abstract class TaskDataBase : RoomDatabase() {
    abstract fun getDoa(): TaskDao

    companion object {
        private var INSTANCE: TaskDataBase? = null
        private val dataBaseName = "Task Database table"
        fun getInstance(context: Context): TaskDataBase {
            if (INSTANCE == null) {

                INSTANCE = Room.databaseBuilder(context, TaskDataBase::class.java, dataBaseName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }
    }

}