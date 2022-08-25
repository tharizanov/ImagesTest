package com.example.test2.app.ui.fragments.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test2.app.databinding.ItemRecyclerHomeBinding
import com.example.test2.app.domains.HomeRecyclerItem
import com.example.test2.app.ui.fragments.home.HomeVM
import com.example.test2.util.EzLog

class HomeRecyclerAdapter(private val vm: HomeVM) : RecyclerView.Adapter<HomeRecyclerViewHolder>() {

    private val itemsList = ArrayList<HomeRecyclerItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewHolder =
        HomeRecyclerViewHolder(
            ItemRecyclerHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: HomeRecyclerViewHolder, position: Int) = holder.bind(itemsList[position], vm)

    override fun getItemCount(): Int = itemsList.size

    fun setItems(items: List<HomeRecyclerItem>?) {
        itemCount.let {
            if (it > 0) {
                itemsList.clear()
                notifyItemRangeRemoved(0, it)
            }
        }

        if (items != null && items.isNotEmpty()) {
            EzLog.d("Set ${items.size} items")
            itemsList.addAll(items)
            notifyItemRangeInserted(0, items.size)
        }
    }

}