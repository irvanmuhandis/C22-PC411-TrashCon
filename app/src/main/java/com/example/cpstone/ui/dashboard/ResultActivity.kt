package com.example.cpstone.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cpstone.R
import com.example.cpstone.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result = intent.getIntExtra("result",0)
        Toast.makeText(this,result.toString(),Toast.LENGTH_SHORT).show()
        setview(binding,result!!)
    }

    private fun setview(binding: ActivityResultBinding,index:Int) {
        var Class = resources.getStringArray(R.array.result)
        val desc = resources.getStringArray(R.array.data_desc)
        val photo = resources.obtainTypedArray(R.array.data_photo)
        val process = resources.getStringArray(R.array.data_process)

        binding.classTrash.text = Class[index]
        binding.description.text = desc[index]
        binding.process.text = process[index]
        binding.ivTrashClassDetail.setImageResource(photo.getResourceId(index,-1))

    }
}