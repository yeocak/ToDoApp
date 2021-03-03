package com.example.todoapp.ui.showtodo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.database.ToDoDatabase
import com.example.todoapp.model.ToDo
import com.example.todoapp.utils.Database
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ShowFragmentViewModel : ViewModel() {

    val changed = MutableLiveData<Boolean>()

    private var changedTitle = false
    private var changedExp = false
    private var changedCheck = false

    fun titleChange(changed: Boolean) {
        changedTitle = changed
        updateChanged()
    }
    fun expChange(changed: Boolean) {
        changedExp = changed
        updateChanged()
    }
    fun checkChange(changed: Boolean) {
        changedCheck = changed
        updateChanged()
    }

    fun resetChanges(){
        checkChange(false)
        expChange(false)
        titleChange(false)
    }

    private fun updateChanged() {
        changed.value = changedCheck || changedExp || changedTitle
    }

    fun setDatabaseToDo(newTitle: String, newExp: String, newChecked: Boolean, position: Int, context: Context) {
        val current = Database.getCurrentList().list[position]

        Database.todoLists[Database.position].list[position].apply {
            comment = newExp
            title = newTitle
            checked = newChecked
        }

        GlobalScope.launch {
            val instance = ToDoDatabase.getInstance(context)
            val new = ToDo(
                newTitle,
                newExp,
                current.listId,
                current.uid,
                newChecked
            )
            instance.toDoDao.updateToDo(new)
        }
    }

    fun deleteDatabaseToDo(position: Int, context: Context) {
        val current = Database.getCurrentList().list[position]
        Database.todoLists[Database.position].list.removeAt(position)

        GlobalScope.launch {
            val instance = ToDoDatabase.getInstance(context)
            instance.toDoDao.deleteToDo(current)
        }
    }
}