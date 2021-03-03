package com.example.todoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.model.ToDo
import com.example.todoapp.model.ToDoList

@Database(entities = [ToDo::class, ToDoList::class], version = 1)
abstract class ToDoDatabase : RoomDatabase() {

    abstract val toDoDao: ToDoDao
    abstract val toDoListDao: ToDoListDao

    companion object {
        @Volatile
        private var INSTANCE: ToDoDatabase? = null

        fun getInstance(context: Context): ToDoDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ToDoDatabase::class.java,
                        "todo_database"
                    ).build()
                }

                return instance
            }
        }
    }

}