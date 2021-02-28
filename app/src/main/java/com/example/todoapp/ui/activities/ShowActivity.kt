package com.example.todoapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todoapp.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}