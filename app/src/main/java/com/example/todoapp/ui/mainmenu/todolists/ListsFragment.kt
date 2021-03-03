package com.example.todoapp.ui.mainmenu.todolists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.databinding.FragmentListsBinding
import com.example.todoapp.ui.mainmenu.MainActivity
import com.example.todoapp.ui.mainmenu.addtodo.AddFragment

class ListsFragment : Fragment() {

    private lateinit var binding: FragmentListsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This is for AddFragment to specify current fragment
        AddFragment.isInCurrent = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListsBinding.inflate(layoutInflater)

        setRV()

        return binding.root
    }

    private fun setRV(){
        // Set adapter of recyclerview

        val adapter = ListsAdapter(activity as MainActivity)

        binding.rvLists.adapter = adapter
        binding.rvLists.layoutManager = LinearLayoutManager(activity as MainActivity)
    }
}