package com.example.todoapp.database

object Database {
    val todoLists = mutableListOf<ToDoList>()
    val currentList = mutableListOf<ToDo>()

    val autoDelete: Boolean = false
}