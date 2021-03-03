package com.example.todoapp.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class ToDoList(

        var name: String,

        @PrimaryKey
        var uid: Int,
) {
        @Ignore
        var list: MutableList<ToDo> = mutableListOf()
}