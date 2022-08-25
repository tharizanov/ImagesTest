package com.example.test2.repository.domains

import com.google.gson.annotations.SerializedName

data class SearchInfoMenuItem(
    @SerializedName("position") val position: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("link") val link: String?,
    @SerializedName("serpapi_link") val serpapiLink: String?
)
