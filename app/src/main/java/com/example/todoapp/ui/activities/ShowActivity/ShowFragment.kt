package com.example.todoapp.ui.activities.ShowActivity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.todoapp.database.Database
import com.example.todoapp.database.ToDo
import com.example.todoapp.databinding.FragmentShowBinding
import com.example.todoapp.ui.activities.MainActivity

class ShowFragment : Fragment() {

    private lateinit var binding: FragmentShowBinding
    private var changedTitle = false
    private var changedExp = false
    private var changedCheck = false

    private var position: Int = -1
    private lateinit var currentToDo: ToDo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowBinding.inflate(layoutInflater)

        position = ShowAdapter.currentShowPosition
        currentToDo = Database.getCurrentList().list[position]

        setFields()

        binding.btnShowBack.setOnClickListener {
            (activity as ShowActivity).finish()
        }

        binding.btnShowCancel.setOnClickListener {
            setFields()
            resetChanges()
        }

        binding.btnShowSave.setOnClickListener {
            Database.todoLists[Database.position].list[position].apply {
                comment = binding.etShowExp.text.toString()
                title = binding.etShowTitle.text.toString()
                checked = binding.cbShowCheck.isChecked
            }
            resetChanges()
        }

        binding.btnShowDelete.setOnClickListener {
            val alertBuilder = AlertDialog.Builder(activity as ShowActivity)
                .setTitle("Delete To Do")
                .setMessage("You really want delete this to do?")

            alertBuilder.apply {
                setPositiveButton("Delete"){ _, _ ->
                    Database.todoLists[Database.position].list.removeAt(position)

                    val intent = Intent((activity as ShowActivity), MainActivity::class.java)
                    (activity as ShowActivity).apply {
                        startActivity(intent)
                        finishAffinity()
                    }
                }
                setNegativeButton("Cancel"){_,_->}
                show()
            }
        }

        binding.etShowTitle.addTextChangedListener {
            changedTitle = (binding.etShowTitle.text.toString() != currentToDo.title)
            checkChanges()
        }
        binding.etShowExp.addTextChangedListener {
            changedExp = (binding.etShowExp.text.toString() != currentToDo.comment)
            checkChanges()
        }
        binding.cbShowCheck.setOnCheckedChangeListener { _, isChecked ->
            changedCheck = (isChecked != currentToDo.checked)
            checkChanges()
        }

        return binding.root
    }

    private fun setFields() {
        binding.etShowTitle.setText(currentToDo.title)
        binding.etShowExp.setText(currentToDo.comment)
        binding.cbShowCheck.isChecked = currentToDo.checked
    }

    private fun resetChanges(){
        changedCheck = false
        changedExp = false
        changedTitle = false
        checkChanges()
    }

    private fun checkChanges(){
        if(changedTitle || changedCheck || changedExp){
            binding.layoutChanges.visibility = VISIBLE
        }
        else{
            binding.layoutChanges.visibility = INVISIBLE
        }
    }
}