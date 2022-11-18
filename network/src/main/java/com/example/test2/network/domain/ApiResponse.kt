package com.example.test2.network.domain

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("search_metadata") val searchMetadata: SearchMetadata?,
    @SerializedName("search_parameters") val searchParams: SearchParams?,
    @SerializedName("search_information") val searchInfo: SearchInfo?,
    @SerializedName("suggested_searches") val suggestedSearches: List<SuggestedSearch>?,
    @SerializedName("images_results") val imageResults: List<ImageResult>?
)
