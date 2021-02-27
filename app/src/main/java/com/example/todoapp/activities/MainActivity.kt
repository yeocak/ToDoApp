package com.example.todoapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
            //Checking for is already on
            if(navController.currentDestination?.label.toString() != "fragment_add"){
                navController.navigate(R.id.action_global_addFragment)

                // Reset bottom navigation bar
                binding.bottomMenuView.menu.findItem(R.id.blank).isChecked = true
            }
        }
    }
}