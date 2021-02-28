package com.example.todoapp.ui.fragments.CurrentFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.database.Database
import com.example.todoapp.databinding.FragmentCurrentBinding
import com.example.todoapp.ui.activities.MainActivity
import com.example.todoapp.ui.fragments.AddFragment.AddFragment

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

        // Settings adapter for To-Dos
        adapter = CurrentAdapter(activity as MainActivity)
        binding.rvCurrent.adapter = adapter
        binding.rvCurrent.layoutManager = LinearLayoutManager(activity as MainActivity)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        // Refresh for changes
        adapter.notifyDataSetChanged()
        AddFragment.isInCurrent = true
    }
}