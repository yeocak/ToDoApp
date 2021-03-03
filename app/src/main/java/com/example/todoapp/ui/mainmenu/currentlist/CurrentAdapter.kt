package com.example.todoapp.ui.mainmenu.currentlist

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.database.ToDoDatabase
import com.example.todoapp.utils.Repository
import com.example.todoapp.databinding.SingleTodoBlockBinding
import com.example.todoapp.model.ToDo
import com.example.todoapp.ui.showtodo.ShowActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CurrentAdapter(
    val context: Context
) : RecyclerView.Adapter<CurrentAdapter.VHCurrentList>() {
    class VHCurrentList(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SingleTodoBlockBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHCurrentList {
        return VHCurrentList(
            LayoutInflater.from(parent.context).inflate(
                R.layout.single_todo_block,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VHCurrentList, position: Int) {
        // This if for last invisible element

        if (position != Repository.todoLists[Repository.position].list.size) {
            val current = Repository.getCurrentList().list[position]

            holder.binding.apply {
                // Setting UI elements

                layoutCurrent.visibility = VISIBLE
                tvSingleTodoTitle.text = current.title
                tvSingleTodoComment.text = current.comment
                cbSingleTodoChecked.isChecked = current.checked

                cbSingleTodoChecked.setOnCheckedChangeListener { _, isChecked ->
                    Repository.todoLists[Repository.position].list[position].checked = isChecked

                    GlobalScope.launch {
                        // Update via room database

                        val instance = ToDoDatabase.getInstance(context)
                        val updateToDo = ToDo(
                            current.title,
                            current.comment,
                            current.listId,
                            current.uid,
                            isChecked
                        )

                        instance.toDoDao.updateToDo(updateToDo)
                    }

                }

                layoutCurrent.setOnClickListener {
                    // Start ShowActivity for detailed To-Do
                    val intent = Intent(context, ShowActivity::class.java)
                    intent.putExtra("current", position)
                    context.startActivity(intent)
                }
            }
        } else {
            holder.binding.layoutCurrent.visibility = INVISIBLE
        }

    }

    override fun getItemCount(): Int {
        // +1 is for bottom space
        return Repository.todoLists[Repository.position].list.size + 1
    }

}