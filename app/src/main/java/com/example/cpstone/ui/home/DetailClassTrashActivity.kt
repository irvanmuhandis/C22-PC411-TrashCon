package com.example.cpstone.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cpstone.databinding.ActivityDetailClassTrashBinding

class DetailClassTrashActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailClassTrashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailClassTrashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.textView2.text = "asadad"
    }
}