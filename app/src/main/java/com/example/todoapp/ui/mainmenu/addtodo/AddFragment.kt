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
import com.example.todoapp.utils.Repository
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
        // Setting edit text etc. from Repository

        if (isInCurrent) {
            binding.tvAddTitle.text = Repository.getCurrentList().name
        } else {
            binding.tvAddTitle.text = getString(R.string.text_view_add_list)
            binding.tvAddToToDoTitle.text = getString(R.string.text_view_list_name)
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
        // Adding new to do

        var uid: Long

        val newToDo = ToDo(
            binding.etAddTitle.text.toString(),
            binding.etAddExp.text.toString(),
            Repository.getCurrentList().uid
        )

        GlobalScope.launch {
            // Adding to room database

            val dao = ToDoDatabase.getInstance((activity as MainActivity).application).toDoDao

            uid = dao.insertToDo(newToDo)

            newToDo.uid = uid

            Repository.todoLists[Repository.position].list.add(newToDo)
        }
    }

    private fun addList() {
        // Adding new to-do list

        val newId = if (Repository.todoLists.size > 0) {
            Repository.todoLists[Repository.todoLists.lastIndex].uid + 1
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

        Repository.todoLists.add(newList)
    }

    private fun clearAreas() {
        binding.etAddExp.setText("")
        binding.etAddTitle.setText("")
    }
}