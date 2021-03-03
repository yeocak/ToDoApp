package com.example.todoapp.database

import androidx.room.*
import com.example.todoapp.model.ToDo

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToDo(new: ToDo): Long

    @Delete
    suspend fun deleteToDo(todo: ToDo)

    @Query("SELECT * FROM todo WHERE listId = :listUid")
    suspend fun selectToDo(listUid: Int): List<ToDo>

    @Query("DELETE FROM todo WHERE listId = :listid")
    suspend fun deleteListOfToDos(listid: Int)

    @Update(entity = ToDo::class)
    suspend fun updateToDo(obj: ToDo)
}