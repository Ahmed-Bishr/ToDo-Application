package com.example.todoapplication

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class Data : RoomDatabase() {
    abstract fun getDoa(): TaskDao

    companion object {
        private var INSTANCE: Data? = null
        private val dataBaseName = "Task Database table"
        fun getInstance(context: Context): Data {
            if (INSTANCE == null) {

                INSTANCE = Room.databaseBuilder(context, Data::class.java, dataBaseName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }
    }

}