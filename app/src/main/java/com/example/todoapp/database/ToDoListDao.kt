package com.example.todoapp.database

import androidx.room.*
import com.example.todoapp.model.ToDoList

@Dao
interface ToDoListDao {
    // Dao for single To-Do

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(list: ToDoList)

    @Query("SELECT * FROM ToDoList")
    suspend fun takeListIds(): List<ToDoList>

    @Delete
    suspend fun deleteList(member: ToDoList)

}