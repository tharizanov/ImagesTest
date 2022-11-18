package com.example.test2.network.domain

import com.google.gson.annotations.SerializedName

data class SuggestedSearch(
    @SerializedName("name") val name: String?,
    @SerializedName("link") val link: String?,
    @SerializedName("chips") val chips: String?,
    @SerializedName("serpapi_link") val serpapiLink: String?,
    @SerializedName("thumbnail") val thumbnail: String?
)
