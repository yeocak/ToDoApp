package com.example.todoapp.database

data class ToDoList(
        val name: String,
        val id: Int,
        val list: MutableList<ToDo> = mutableListOf()
)