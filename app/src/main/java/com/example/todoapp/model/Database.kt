package com.example.todoapp.model

object Database {
    val todoLists = mutableListOf<ToDoList>()
    var position = 0

    fun getCurrentList(): ToDoList = todoLists[position]
}