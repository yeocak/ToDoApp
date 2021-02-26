package com.example.todoapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setting bottom navigation bar
        val navController = findNavController(R.id.frMenu)
        binding.bottomMenuView.setupWithNavController(navController)

        binding.bottomMenuAdd.setOnClickListener {

            

            // Reset bottom navigation bar
            binding.bottomMenuView.menu.findItem(R.id.blank).isChecked = true
        }
    }
}