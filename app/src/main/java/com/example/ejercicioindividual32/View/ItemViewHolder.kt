package com.example.ejercicioindividual32.View

import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicioindividual32.Model.Item
import com.example.ejercicioindividual32.databinding.ItemRecyclerBinding

class ItemViewHolder(private val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item){
        binding.nombreItem.text = item.itemName
        binding.precioItem.text = "$${item.itemPrice.toString()} c/u"
        binding.cantidadItem.text = "cant. ${item.itemCant.toString()}"
    }
}