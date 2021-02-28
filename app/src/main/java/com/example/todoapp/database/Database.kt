package com.example.todoapp.database

object Database {
    val geciciList = ToDoList("İlk Liste",1, mutableListOf())

    val todoLists = mutableListOf<ToDoList>(geciciList)
    var position = 0

    var autoDelete: Boolean = false

    fun getCurrentList(): ToDoList = todoLists[position]
}