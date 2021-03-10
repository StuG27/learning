package com.skillbox.viewmodelandnavigation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.skillbox.myapplication.databinding.ActivityAppBinding

class AppActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}