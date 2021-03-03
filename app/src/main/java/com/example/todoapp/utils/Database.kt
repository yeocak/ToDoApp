package com.example.todoapp.utils

import com.example.todoapp.model.ToDoList

object Database {
    val todoLists = mutableListOf<ToDoList>()
    var position = 0

    fun getCurrentList(): ToDoList = todoLists[position]
}