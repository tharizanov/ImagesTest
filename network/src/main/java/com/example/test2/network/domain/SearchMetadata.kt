package com.example.test2.network.domain

import com.google.gson.annotations.SerializedName

data class SearchMetadata(
    @SerializedName("id") val id: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("json_endpoint") val jsonEndpoint: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("processed_at") val processedAt: String?,
    @SerializedName("google_url") val googleUrl: String?,
    @SerializedName("raw_html_file") val rawHtmlFile: String?,
    @SerializedName("total_time_taken") val totalTimeTaken: Double?
)
