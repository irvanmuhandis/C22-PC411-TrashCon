package com.example.cpstone.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cpstone.data.TrashClass
import com.example.cpstone.databinding.ActivityDetailClassTrashBinding

class DetailClassTrashActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailClassTrashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailClassTrashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        val item = intent.getParcelableExtra<TrashClass>("item")


        binding.classTrash.text = item?.name
        binding.description.text = item?.description
        binding.process.text = item?.process
        binding.ivTrashClassDetail.setImageResource(item!!.photo)
    }




}