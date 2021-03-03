package com.example.todoapp.utils

import com.example.todoapp.model.ToDoList

object Repository {
    // This list filling with room database
    val todoLists = mutableListOf<ToDoList>()

    // This position for current To-Do list
    var position = 0

    fun getCurrentList(): ToDoList = todoLists[position]
}