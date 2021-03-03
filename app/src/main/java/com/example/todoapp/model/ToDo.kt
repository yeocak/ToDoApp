package com.example.todoapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDo(
    var title: String,
    var comment: String,
    val listId: Int,

    @PrimaryKey(autoGenerate = true)
    var uid: Long = 0,

    var checked: Boolean = false
)