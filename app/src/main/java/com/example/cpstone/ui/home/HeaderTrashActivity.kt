package com.example.cpstone.ui.home

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cpstone.R
import com.example.cpstone.databinding.ActivityDetailClassTrashBinding
import com.example.cpstone.databinding.ActivityHeaderTrashBinding

class HeaderTrashActivity : AppCompatActivity() {
    lateinit var binding: ActivityHeaderTrashBinding

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeaderTrashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val photo = resources.obtainTypedArray(R.array.tutorial_photo)
        val text = resources.getStringArray(R.array.tutorial_text)
        binding.ivYa1.setImageResource(photo.getResourceId(0,-1))
        binding.ivYa2.setImageResource(photo.getResourceId(0,-1))
        binding.ivYa3.setImageResource(photo.getResourceId(0,-1))
        binding.ivBlur.setImageResource(photo.getResourceId(1,-1))
        binding.ivZoom.setImageResource(photo.getResourceId(2,-1))
        binding.ivMix.setImageResource(photo.getResourceId(3,-1))

        binding.caraPakai.text = text[0]
        binding.tipsGambar.text = text[1]
        binding.stepOne.text = text[2]
        binding.stepTwo.text = text[3]
        binding.stepThree.text = text[4]
    }
}