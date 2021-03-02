package com.example.todoapp.ui.showtodo

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.model.Database
import com.example.todoapp.model.ToDo
import com.example.todoapp.databinding.FragmentShowBinding
import com.example.todoapp.ui.mainmenu.MainActivity

class ShowFragment : Fragment() {

    private lateinit var binding: FragmentShowBinding
    private lateinit var viewModel : ShowFragmentViewModel

    private var position: Int = -1
    private lateinit var currentToDo: ToDo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setting viewmodel
        viewModel = ViewModelProvider(activity as ShowActivity).get(ShowFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowBinding.inflate(layoutInflater)

        // Visibility of cancel/save button
        viewModel.changed.observe((activity as ShowActivity), {
            if(it){
                binding.layoutChanges.visibility = VISIBLE
            }else{
                binding.layoutChanges.visibility = INVISIBLE
            }
        })

        // Taking current to do with position from database
        position = ShowAdapter.currentShowPosition
        currentToDo = Database.getCurrentList().list[position]

        setFields()

        // Go back
        binding.btnShowBack.setOnClickListener {
            (activity as ShowActivity).finish()
        }

        // Cancel changes
        binding.btnShowCancel.setOnClickListener {
            setFields()
            viewModel.resetChanges()
        }

        // Save changes
        binding.btnShowSave.setOnClickListener {
            viewModel.setDatabaseToDo(
                binding.etShowTitle.text.toString(),
                binding.etShowExp.text.toString(),
                binding.cbShowCheck.isChecked,
                position)

            viewModel.resetChanges()
        }

        // Stage of deleting a to do
        binding.btnShowDelete.setOnClickListener {
            val alertBuilder = AlertDialog.Builder(activity as ShowActivity)
                .setTitle("Delete To Do")
                .setMessage("You really want delete this to do?")

            alertBuilder.apply {
                setPositiveButton("Delete"){ _, _ ->
                    viewModel.deleteDatabaseToDo(position)

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

        // These are for checking changes of ui elements
        binding.etShowTitle.addTextChangedListener {
            val change = (binding.etShowTitle.text.toString() != currentToDo.title)
            viewModel.titleChange(change)
        }
        binding.etShowExp.addTextChangedListener {
            val change = (binding.etShowExp.text.toString() != currentToDo.comment)
            viewModel.expChange(change)
        }
        binding.cbShowCheck.setOnCheckedChangeListener { _, isChecked ->
            val change = (isChecked != currentToDo.checked)
            viewModel.checkChange(change)
        }

        return binding.root
    }

    private fun setFields() {
        binding.etShowTitle.setText(currentToDo.title)
        binding.etShowExp.setText(currentToDo.comment)
        binding.cbShowCheck.isChecked = currentToDo.checked
    }
}