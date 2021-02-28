package com.example.todoapp.database

data class ToDo(
    var title: String,
    var comment: String,
    var checked: Boolean = false
)