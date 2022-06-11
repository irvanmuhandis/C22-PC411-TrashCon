package com.example.cpstone.ui.history

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.cpstone.data.ImageUpload
import com.example.cpstone.databinding.ItemHistoryBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HistoryAdapter(val list: ArrayList<ImageUpload>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryHolder>() {

    private var onHistoryClick: OnHistoryClick? = null

    fun setOnhistoryClick(onStoryClick: OnHistoryClick) {
        this.onHistoryClick = onStoryClick
    }

    interface OnHistoryClick {
        fun onHistoryClicked(packet: ImageUpload)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        return HistoryHolder(
            ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class HistoryHolder(private var binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ImageUpload) {
            binding.tvResult.text = data.result
            var date = Date(data.timestamp!!)
            val pattern = "MMM dd, yyyy EEE h:mm a"
            val textDate = SimpleDateFormat(pattern).format(date)
            binding.tvDate.text = textDate
            Glide.with(itemView)
                .load(data.photo)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.ivTrashClass)

            binding.root.setOnClickListener { onHistoryClick?.onHistoryClicked(data) }
        }
    }
}


