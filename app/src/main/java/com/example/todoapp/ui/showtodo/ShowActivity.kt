package com.example.todoapp.ui.showtodo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.utils.Repository
import com.example.todoapp.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowBinding
    private var currentPosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get index of selected to-do
        currentPosition = intent.getIntExtra("current", -1)

        // Set adapter of ViewPager
        val adapter = ShowAdapter(
            Repository.getCurrentList().list.size,
            supportFragmentManager,
            lifecycle
        )

        binding.vpShow.adapter = adapter
        binding.vpShow.currentItem = currentPosition
    }

}