package com.example.test2.network.domain

import com.google.gson.annotations.SerializedName

data class SearchParams(
    @SerializedName("engine") val engine: String?,
    @SerializedName("q") val query: String?,
    @SerializedName("google_domain") val googleDomain: String?,
    @SerializedName("ijn") val ijn: String?,
    @SerializedName("device") val device: String?,
    @SerializedName("tbm") val tbm: String?
)
