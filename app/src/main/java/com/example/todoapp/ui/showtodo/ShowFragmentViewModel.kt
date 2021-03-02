package com.example.todoapp.ui.showtodo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.model.Database

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

    fun setDatabaseToDo(newTitle: String, newExp: String, newChecked: Boolean, position: Int) {
        Database.todoLists[Database.position].list[position].apply {
            comment = newExp
            title = newTitle
            checked = newChecked
        }
    }

    fun deleteDatabaseToDo(position: Int) {
        Database.todoLists[Database.position].list.removeAt(position)
    }
}