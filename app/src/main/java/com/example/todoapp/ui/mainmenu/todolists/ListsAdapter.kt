package com.example.todoapp.ui.mainmenu.todolists

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.database.ToDoDatabase
import com.example.todoapp.utils.Repository
import com.example.todoapp.databinding.SingleListBlockBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListsAdapter(val contexting: Context) : RecyclerView.Adapter<ListsAdapter.VHList>() {

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
        // This if is for invisibility of last bottom space

        if (position != Repository.todoLists.size) {
            val current = Repository.todoLists[position]

            holder.binding.apply {
                // Set UI elements
                layoutList.visibility = VISIBLE
                btnSingleListSelect.text = current.name

                btnSingleListSelect.setOnClickListener {
                    // Selecting list
                    Repository.position = position
                    notifyDataSetChanged()

                    // This SharedPreferences for selected list index
                    val preference = contexting.getSharedPreferences("position",
                        AppCompatActivity.MODE_PRIVATE
                    )
                    preference.edit().putInt("position", position).apply()
                }

                btnSingleListDelete.setOnClickListener {
                    // Delete a list
                    Repository.todoLists.removeAt(position)
                    if (Repository.position != 0) {
                        Repository.position -= 1
                    }

                    GlobalScope.launch {
                        // Delete from room database

                        val instance = ToDoDatabase.getInstance(contexting)
                        instance.toDoListDao.deleteList(current)
                        instance.toDoDao.deleteListOfToDos(current.uid)
                    }

                    notifyDataSetChanged()
                }

                // This enable is for marking the selected list
                btnSingleListSelect.isEnabled = (Repository.position != position)
            }
        } else {
            holder.binding.layoutList.visibility = INVISIBLE
        }

    }

    override fun getItemCount(): Int {
        // +1 is for bottom space
        return Repository.todoLists.size + 1
    }

}