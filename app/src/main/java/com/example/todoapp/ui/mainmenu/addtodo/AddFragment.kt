package com.example.todoapp.ui.mainmenu.addtodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import com.example.todoapp.R
import com.example.todoapp.database.ToDoDatabase
import com.example.todoapp.utils.Database
import com.example.todoapp.model.ToDo
import com.example.todoapp.model.ToDoList
import com.example.todoapp.databinding.FragmentAddBinding
import com.example.todoapp.ui.mainmenu.MainActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding

    companion object {
        // This is for data sharing between the fragments
        var isInCurrent = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(layoutInflater)

        setFields()

        binding.btnAddSave.setOnClickListener {
            if (checkFields()) {
                if (isInCurrent) {
                    addToDo()
                } else {
                    addList()
                }
                clearAreas()
            }
        }

        binding.btnAddCancel.setOnClickListener {
            findNavController((activity as MainActivity), R.id.frMenu).popBackStack()
        }

        return binding.root
    }

    private fun setFields() {
        if (isInCurrent) {
            binding.tvAddTitle.text = Database.getCurrentList().name
        } else {
            binding.tvAddTitle.text = "Add new To Do List"
            binding.tvAddToToDoTitle.text = "List Name"
            binding.tvAddToDoExp.visibility = INVISIBLE
            binding.etAddExp.visibility = INVISIBLE
        }
    }

    private fun checkFields(): Boolean {
        // Check for if the fields are empty
        if (binding.etAddTitle.text.isNotEmpty() && (binding.etAddExp.text.isNotEmpty() || !isInCurrent)) {
            return true
        }
        Toast.makeText(
            (activity as MainActivity),
            "Please, fill in the blanks!",
            Toast.LENGTH_SHORT
        ).show()
        return false
    }

    private fun addToDo() {
        var uid: Long

        val newToDo = ToDo(
            binding.etAddTitle.text.toString(),
            binding.etAddExp.text.toString(),
            Database.getCurrentList().uid
        )

        GlobalScope.launch {
            // Adding to room database

            val dao = ToDoDatabase.getInstance((activity as MainActivity).application).toDoDao

            uid = dao.addToDo(newToDo)

            newToDo.uid = uid

            Database.todoLists[Database.position].list.add(newToDo)
        }
    }

    private fun addList() {
        val newId = if (Database.todoLists.size > 0) {
            Database.todoLists[Database.todoLists.lastIndex].uid + 1
        } else {
            0
        }
        val newList = ToDoList(
            binding.etAddTitle.text.toString(),
            newId,
        )

        GlobalScope.launch {
            // Adding to room database

            val dao = ToDoDatabase.getInstance(activity as MainActivity).toDoListDao
            dao.insertList(newList)
        }

        Database.todoLists.add(newList)
    }

    private fun clearAreas() {
        binding.etAddExp.setText("")
        binding.etAddTitle.setText("")
    }
}