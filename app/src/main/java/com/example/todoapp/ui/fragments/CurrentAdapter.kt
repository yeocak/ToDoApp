package com.example.todoapp.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.database.ToDo
import com.example.todoapp.databinding.SingleTodoBlockBinding

class CurrentAdapter(
    private val currentList: MutableList<ToDo>
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
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

}