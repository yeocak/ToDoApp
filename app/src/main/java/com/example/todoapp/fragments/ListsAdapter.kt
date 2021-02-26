package com.example.todoapp.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.database.ToDo
import com.example.todoapp.databinding.SingleListBlockBinding

class ListsAdapter(
    val currentList: MutableList<MutableList<ToDo>>
) : RecyclerView.Adapter<ListsAdapter.VHList>() {

    class VHList(itemView: View): RecyclerView.ViewHolder(itemView) {
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
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

}