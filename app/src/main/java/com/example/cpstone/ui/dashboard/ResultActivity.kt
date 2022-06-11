package com.example.cpstone.ui.dashboard

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.cpstone.R
import com.example.cpstone.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result = intent.getIntExtra("result", 0)

        val photo = intent.getStringExtra("photo")
        setview(binding, result!!, photo!!)


    }


    private fun setview(binding: ActivityResultBinding, index: Int, image: String) {
        var Class = resources.getStringArray(R.array.result)
        val desc = resources.getStringArray(R.array.data_desc)
        val process = resources.getStringArray(R.array.data_process)



        binding.classTrash.text = Class[index]
        binding.description.text = desc[index]
        binding.process.text = process[index]
        binding.ivTrashClassDetail.setImageURI(image.toUri())
    }


}