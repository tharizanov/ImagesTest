package com.example.test2.app.binding

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.test2.app.R
import com.example.test2.app.domains.HomeRecyclerItem
import com.example.test2.app.ui.fragments.home.adapter.HomeRecyclerAdapter

@BindingAdapter("visible")
fun setVisibility(view: View, visible: Boolean) {
    view.isVisible = visible
}

@BindingAdapter("items")
fun setHomeRecyclerItems(view: RecyclerView, items: List<HomeRecyclerItem>?) {
    (view.adapter as? HomeRecyclerAdapter)?.setItems(items)
}

@BindingAdapter("imageUrl", "placeholderId", requireAll = false)
fun setImageViewContent(view: ImageView, imageUrl: String?, @DrawableRes placeholderId: Int?) {
    Glide.with(view)
        .load(imageUrl)
        .skipMemoryCache(false)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .placeholder(placeholderId ?: R.drawable.ic_launcher_background)
        .into(view)
}