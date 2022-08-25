package com.example.test2.persistance.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageItem(
    @PrimaryKey val position: Int?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "link") val link: String?,
    @ColumnInfo(name = "original_url") val originalUrl: String?,
    @ColumnInfo(name = "thumbnail_url") val thumbnailUrl: String?
)
