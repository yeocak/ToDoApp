package com.example.todoapp.ui.mainmenu.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater)

        // For go github button
        binding.btnSettingsGithub.setOnClickListener {
            goGithub()
        }

        return binding.root
    }

    private fun goGithub(){
        val githubUrl = "https://github.com/yeocak/ToDoApp"
        val githubAction = Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl))
        startActivity(githubAction)
    }
}