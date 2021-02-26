package com.example.todoapp.database

data class ToDo(
    val title: String,
    val checked: Boolean = false,
    val comment: String
)
