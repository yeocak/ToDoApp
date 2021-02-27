package com.example.todoapp.database

data class ToDoList(
        val name: String,
        val id: String,
        val list: MutableList<ToDo> = mutableListOf()
)
