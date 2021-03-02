package com.example.todoapp.model

data class ToDo(
    var title: String,
    var comment: String,
    var checked: Boolean = false
)