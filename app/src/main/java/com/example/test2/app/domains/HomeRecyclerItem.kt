package com.example.test2.app.domains

/**
 * Data object for the view layer, intended to populate a [androidx.recyclerview.widget.RecyclerView].
 */
data class HomeRecyclerItem(
    val position: Int?,
    val title: String?,
    val link: String?,
    val originalUrl: String?,
    val thumbnailUrl: String?
)