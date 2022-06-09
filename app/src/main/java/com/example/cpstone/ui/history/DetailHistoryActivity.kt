package com.example.cpstone.ui.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.cpstone.R
import com.example.cpstone.data.ImageUpload
import com.example.cpstone.databinding.ActivityDetailHistoryBinding

class DetailHistoryActivity : AppCompatActivity() {

    lateinit var binding : ActivityDetailHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result = intent.getParcelableExtra<ImageUpload>("resultHistory")
        setview2(binding, result!!)
    }
    private fun setview2(binding: ActivityDetailHistoryBinding, packet: ImageUpload) {
        var Class = resources.getStringArray(R.array.result)
        val desc = resources.getStringArray(R.array.data_desc)
        val process = resources.getStringArray(R.array.data_process)

        val index = packet.indexClass
        val photo = packet.photo

        binding.classTrash.text = Class[index!!]
        binding.description.text = desc[index!!]
        binding.process.text = process[index!!]
        Glide.with(this)
            .load(photo)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.ivTrashClassDetail)
    }
}