package com.example.cpstone.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cpstone.R
import com.example.cpstone.data.TrashClass

class TrashAdapter(val listtrashes: ArrayList<Any>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val ITEM_HEADER = 0
        private const val ITEM_MENU = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (listtrashes[position]) {
            is String -> ITEM_HEADER
            is TrashClass -> ITEM_MENU
            else -> throw IllegalArgumentException("Undefined view type")
        }
    }

    class ViewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.iv_trashClass)
        val name: TextView = itemView.findViewById(R.id.tv_nameTrashClass)

        fun bindContent(Item: TrashClass){
            img.setImageResource(Item.photo)
            name.text = Item.name
        }
    }

    class HeaderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val header : TextView = itemView.findViewById(R.id.judul_tutor)

        fun bindContent(text: String){
            header.text = text
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val view: View =
//            LayoutInflater.from(parent.context).inflate(R.layout.item_column, parent, false)
//        return ViewHolder(view)
        return when (viewType) {
            ITEM_HEADER -> HeaderHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row,parent,false))
            ITEM_MENU -> ViewsHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_column,parent,false))
            else -> throw throw IllegalArgumentException("Undefined view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType){
            ITEM_HEADER -> {
                val headerHolder = holder as HeaderHolder
                headerHolder.bindContent(listtrashes[position] as String)
            }
            ITEM_MENU -> {
                val itemHolder = holder as ViewsHolder
                itemHolder.bindContent(listtrashes[position] as TrashClass)
            }
            else -> throw IllegalArgumentException("Undefined view type")
        }


//        val (name, photo) = listtrashes[position]
//        holder.img.setImageResource(photo)
//        holder.name.text = name
    }

    override fun getItemCount(): Int {
        return listtrashes.size
    }
}