package com.example.test2.app.domains

import com.example.test2.persistance.database.ImageItem
import com.example.test2.repository.domains.ApiResponse

class DomainConvertor {

    fun makeRecyclerItemsFromResponse(response: ApiResponse): List<HomeRecyclerItem> =
        ArrayList<HomeRecyclerItem>().apply {
            response.imageResults?.forEach { item ->
                add(HomeRecyclerItem(
                    position = item.position,
                    title = item.title,
                    link = item.link,
                    originalUrl = item.original,
                    thumbnailUrl = item.thumbnail,
                    isCreatedFromCache = false
                ))
            }
        }

    fun makeRecyclerItemsFromDatabase(items: List<ImageItem>): List<HomeRecyclerItem> =
        ArrayList<HomeRecyclerItem>().apply {
            for (item in items) {
                add(HomeRecyclerItem(
                    position = item.position,
                    title = item.title,
                    link = item.link,
                    originalUrl = item.originalUrl,
                    thumbnailUrl = item.thumbnailUrl,
                    isCreatedFromCache = true
                ))
            }
        }

    fun makeDatabaseItemsFromRecycler(items: List<HomeRecyclerItem>): List<ImageItem> =
        ArrayList<ImageItem>().apply {
            for (item in items) {
                add(ImageItem(
                    position = item.position,
                    title = item.title,
                    link = item.link,
                    originalUrl = item.originalUrl,
                    thumbnailUrl = item.thumbnailUrl
                ))
            }
        }

}