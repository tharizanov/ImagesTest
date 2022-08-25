package com.example.test2.repository.domains

import com.google.gson.annotations.SerializedName

data class SearchInfo(
    @SerializedName("image_results_state") val imageResultsState: String?,
    @SerializedName("query_displayed") val queryDisplayed: String?,
    @SerializedName("menu_items") val menuItems: List<SearchInfoMenuItem>?
)
