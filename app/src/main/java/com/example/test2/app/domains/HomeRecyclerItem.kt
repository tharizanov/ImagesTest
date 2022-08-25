package com.example.test2.app.domains

data class HomeRecyclerItem(
    val position: Int?,
    val title: String?,
    val link: String?,
    val originalUrl: String?,
    val thumbnailUrl: String?,
    val isCreatedFromCache: Boolean = false
)
