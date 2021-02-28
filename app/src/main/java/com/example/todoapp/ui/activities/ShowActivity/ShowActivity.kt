package com.example.todoapp.ui.activities.ShowActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.todoapp.database.Database
import com.example.todoapp.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowBinding
    private var currentPosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentPosition = intent.getIntExtra("current", -1)

        val fragmentList = mutableListOf<Fragment>()
        for(a in 1..Database.getCurrentList().list.size){
            fragmentList.add(ShowFragment())
        }

        val adapter = ShowAdapter(
            fragmentList,
            supportFragmentManager,
            lifecycle
        )

        binding.vpShow.adapter = adapter
        binding.vpShow.currentItem = currentPosition
    }

}