package com.example.todoapp.ui.fragments.ListsFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.database.Database
import com.example.todoapp.database.ToDoList
import com.example.todoapp.databinding.SingleListBlockBinding

class ListsAdapter() : RecyclerView.Adapter<ListsAdapter.VHList>() {

    class VHList(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SingleListBlockBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHList {
        return VHList(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.single_list_block,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: VHList, position: Int) {
        val current = Database.todoLists[position]

        holder.binding.apply {
            btnSingleListSelect.text = current.name

            btnSingleListSelect.setOnClickListener {
                Database.position = position
                notifyDataSetChanged()
            }

            btnSingleListSelect.isEnabled = (Database.position != position)
        }
    }

    override fun getItemCount(): Int {
        return Database.todoLists.size
    }

}