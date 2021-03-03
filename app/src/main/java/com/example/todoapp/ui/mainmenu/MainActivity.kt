package com.example.todoapp.ui.mainmenu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.todoapp.R
import com.example.todoapp.database.ToDoDatabase
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.utils.Database
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Main).launch {
            withContext(Dispatchers.Default) {
                // Taking all To Dos via Room Database
                val instance = ToDoDatabase.getInstance(this@MainActivity)
                val todoRespository = instance.toDoDao
                val takedLists = instance.toDoListDao.takeListIds()

                Database.todoLists.addAll(takedLists)
                for (a in takedLists.indices) {
                    val newTodos = todoRespository.selectToDo(takedLists[a].uid)
                    takedLists[a].list.addAll(newTodos)
                }
            }

            withContext(Main){
                setNavigate()

                // Refresh navigate after taking data from room
                navController.navigate(R.id.action_global_currentFragment)
            }
        }

        // Setting add button
        binding.bottomMenuAdd.setOnClickListener {
            // Checking for is it already on
            if (navController.currentDestination?.label.toString() != "fragment_add") {
                navController.navigate(R.id.action_global_addFragment)

                // Reset bottom navigation bar
                binding.bottomMenuView.menu.findItem(R.id.blank).isChecked = true
            }
        }
    }

    private fun setNavigate(){
        // Setting bottom navigation bar
        navController = findNavController(R.id.frMenu)
        binding.bottomMenuView.setupWithNavController(navController)
    }
}