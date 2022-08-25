package com.example.test2.app.ui.fragments.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.test2.app.databinding.ItemRecyclerHomeBinding
import com.example.test2.app.domains.HomeRecyclerItem
import com.example.test2.app.ui.fragments.home.HomeVM

class HomeRecyclerViewHolder(private val binding: ItemRecyclerHomeBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: HomeRecyclerItem, vm: HomeVM) {
        binding.item = item
        binding.vm = vm
        binding.executePendingBindings()
    }

}