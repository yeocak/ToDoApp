package com.example.todoapp.ui.mainmenu.currentlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.model.Database
import com.example.todoapp.databinding.FragmentCurrentBinding
import com.example.todoapp.ui.mainmenu.MainActivity
import com.example.todoapp.ui.mainmenu.addtodo.AddFragment

class CurrentFragment : Fragment() {

    private lateinit var binding: FragmentCurrentBinding
    private lateinit var adapter: CurrentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // For AddFragment
        AddFragment.isInCurrent = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrentBinding.inflate(layoutInflater)

        // Setting recyclerview adapter for To-Dos
        setRV()

        return binding.root
    }

    private fun setRV(){
        if(Database.todoLists.size > 0){
            adapter = CurrentAdapter(activity as MainActivity)
            binding.rvCurrent.adapter = adapter
            binding.rvCurrent.layoutManager = LinearLayoutManager(activity as MainActivity)
        }
    }

    override fun onResume() {
        super.onResume()
        AddFragment.isInCurrent = true

        // Refresh for changes
        if(Database.todoLists.size > 0){
            adapter.notifyDataSetChanged()
        }
        else{
            AddFragment.isInCurrent = false
        }
    }
}