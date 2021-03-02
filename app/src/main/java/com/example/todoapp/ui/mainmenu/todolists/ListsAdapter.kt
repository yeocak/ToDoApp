package com.example.todoapp.ui.mainmenu.todolists

import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.model.Database
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

        if (position != Database.todoLists.size) {
            val current = Database.todoLists[position]

            holder.binding.apply {
                layoutList.visibility = VISIBLE
                btnSingleListSelect.text = current.name

                btnSingleListSelect.setOnClickListener {
                    Database.position = position
                    notifyDataSetChanged()
                }

                btnSingleListDelete.setOnClickListener {
                    Database.todoLists.removeAt(position)
                    if (Database.position != 0) {
                        Database.position -= 1
                    }

                    notifyDataSetChanged()
                }

                btnSingleListSelect.isEnabled = (Database.position != position)
            }
        } else {
            holder.binding.layoutList.visibility = INVISIBLE
        }

    }

    override fun getItemCount(): Int {
        // +1 is for bottom space
        return Database.todoLists.size + 1
    }

}