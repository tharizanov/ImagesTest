package com.example.test2.app.domains

import com.example.test2.repository.domains.ApiResponse

class DomainConvertor {

    fun getRecyclerItemsFromResponse(response: ApiResponse): List<HomeRecyclerItem> =
        ArrayList<HomeRecyclerItem>().apply {
            response.imageResults?.forEach {
                add(HomeRecyclerItem(
                    position = it.position,
                    thumbnailUrl = it.thumbnail,
                    originalUrl = it.original,
                    title = it.title,
                    link = it.link
                ))
            }
        }

}