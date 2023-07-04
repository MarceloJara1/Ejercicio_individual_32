package com.example.ejercicioindividual32.View

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicioindividual32.Model.Item
import com.example.ejercicioindividual32.databinding.ItemRecyclerBinding

class ItemAdapter: RecyclerView.Adapter<ItemViewHolder>() {
    private val items = mutableListOf<Item>()

    fun setItems(newItems: List<Item>){
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size
}