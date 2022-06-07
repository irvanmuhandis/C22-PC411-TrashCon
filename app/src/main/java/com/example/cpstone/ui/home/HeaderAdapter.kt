package com.example.cpstone.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cpstone.R
import com.example.cpstone.data.HeaderTutor
import com.example.cpstone.databinding.ItemRowBinding


class HeaderAdapter(private val onClick: (String) -> Unit) : RecyclerView.Adapter<HeaderViewHolder>() {
    private var data: List<String> = emptyList()

    companion object {
        const val VIEW_TYPE = 2222
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        return HeaderViewHolder(
           ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(data[position], onClick)
    }

    override fun getItemCount(): Int = data.size

    fun updateData(data: List<String>) {
        this.data = data
        notifyDataSetChanged()
    }
}

class HeaderViewHolder(
    private val binding: ItemRowBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: String, onClick: (String) -> Unit) {
        binding.judulTutor.text = data
        binding.root.setOnClickListener { onClick(data) }
    }
}
