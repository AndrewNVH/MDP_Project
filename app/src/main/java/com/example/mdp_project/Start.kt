package com.example.mdp_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mdp_project.databinding.ActivityMainBinding
import com.example.mdp_project.databinding.ActivityStartBinding

class Start : AppCompatActivity() {
    lateinit var  binding: ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}