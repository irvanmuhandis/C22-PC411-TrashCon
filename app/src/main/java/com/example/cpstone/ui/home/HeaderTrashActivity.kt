package com.example.cpstone.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cpstone.R
import com.example.cpstone.databinding.ActivityDetailClassTrashBinding

class HeaderTrashActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailClassTrashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailClassTrashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }
}