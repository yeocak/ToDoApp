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
import com.example.todoapp.utils.Database
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

        if (position != Database.todoLists[Database.position].list.size) {
            val current = Database.getCurrentList().list[position]
            Log.d("Testing", current.uid.toString())

            holder.binding.apply {
                layoutCurrent.visibility = VISIBLE
                tvSingleTodoTitle.text = current.title
                tvSingleTodoComment.text = current.comment
                cbSingleTodoChecked.isChecked = current.checked

                cbSingleTodoChecked.setOnCheckedChangeListener { _, isChecked ->
                    Database.todoLists[Database.position].list[position].checked = isChecked

                    GlobalScope.launch {
                        val instance = ToDoDatabase.getInstance(context)
                        val updateToDo = ToDo(
                            current.title,
                            current.comment,
                            current.listId,
                            current.uid,
                            isChecked
                        )

                        Log.d("Testing",updateToDo.toString())

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
        return Database.todoLists[Database.position].list.size + 1
    }

}