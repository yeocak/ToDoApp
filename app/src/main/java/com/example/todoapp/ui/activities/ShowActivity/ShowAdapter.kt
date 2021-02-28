package com.example.todoapp.ui.activities.ShowActivity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ShowAdapter(
    private val currentFragmentList: MutableList<Fragment>,
    fm: FragmentManager,
    lc: Lifecycle
) : FragmentStateAdapter(fm, lc) {

    override fun getItemCount(): Int {
        return currentFragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        currentShowPosition = position
        return currentFragmentList[position]
    }

    companion object{
        var currentShowPosition = -1
    }

}