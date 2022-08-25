package com.example.test2.repository.domains

import com.google.gson.annotations.SerializedName

data class ImageResult(
    @SerializedName("position") val position: Int?,
    @SerializedName("thumbnail") val thumbnail: String?,
    @SerializedName("source") val source: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("link") val link: String?,
    @SerializedName("original") val original: String?,
    @SerializedName("is_product") val isProduct: Boolean?,
)