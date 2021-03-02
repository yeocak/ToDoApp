package com.example.todoapp.ui.showtodo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ShowAdapter(
    private val fragmentSize: Int,
    fm: FragmentManager,
    lc: Lifecycle
) : FragmentStateAdapter(fm, lc) {

    override fun getItemCount(): Int {
        return fragmentSize
    }

    override fun createFragment(position: Int): Fragment {
        currentShowPosition = position
        return ShowFragment()
    }

    companion object{
        var currentShowPosition = -1
    }

}