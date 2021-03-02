package com.example.todoapp.model

data class ToDoList(
        val name: String,
        val id: Int,
        val list: MutableList<ToDo> = mutableListOf()
)