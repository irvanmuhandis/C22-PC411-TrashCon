package com.example.cpstone.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cpstone.R
import com.example.cpstone.data.HeaderTutor
import com.example.cpstone.data.TrashClass
import com.example.cpstone.databinding.ItemColumnBinding

class TrashAdapter(
    private val onClick: (TrashClass) -> Unit
) : RecyclerView.Adapter<TrashViewHolder>() {
    private var data: List<TrashClass> = emptyList()

    companion object {
        const val VIEW_TYPE = 1111
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrashViewHolder {
        return TrashViewHolder(
            ItemColumnBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE
    }

    override fun onBindViewHolder(holder:TrashViewHolder, position: Int) {
        holder.bind(data[position], onClick)
    }

    override fun getItemCount(): Int = data.size

    fun updateData(data: List<TrashClass>) {
        this.data = data
        notifyDataSetChanged()
    }


}

class TrashViewHolder(
    private val binding: ItemColumnBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: TrashClass, onClick: (TrashClass) -> Unit) {
        binding.tvNameTrashClass.text = data.name
        binding.ivTrashClass.setImageResource(data.photo)
        binding.root.setOnClickListener { onClick(data) }
    }
}
