package com.example.test2.app.domains

import com.example.test2.persistance.database.ImageItem
import com.example.test2.repository.domains.ApiResponse

/**
 * Singleton class responsible for converting domains from different layers of the application.
 */
class DomainConvertor {

    /**
     * Convert an API response object to a [List] of UI presentable [HomeRecyclerItem] objects.
     */
    fun makeRecyclerItemsFromResponse(response: ApiResponse): List<HomeRecyclerItem> =
        ArrayList<HomeRecyclerItem>().apply {
            response.imageResults?.forEach { item ->
                add(HomeRecyclerItem(
                    position = item.position,
                    title = item.title,
                    link = item.link,
                    originalUrl = item.original,
                    thumbnailUrl = item.thumbnail
                ))
            }
        }

    /**
     * Convert local database objects to a [List] of UI presentable [HomeRecyclerItem] objects.
     */
    fun makeRecyclerItemsFromDatabase(items: List<ImageItem>): List<HomeRecyclerItem> =
        ArrayList<HomeRecyclerItem>().apply {
            for (item in items) {
                add(HomeRecyclerItem(
                    position = item.position,
                    title = item.title,
                    link = item.link,
                    originalUrl = item.originalUrl,
                    thumbnailUrl = item.thumbnailUrl
                ))
            }
        }

    /**
     * Convert the UI [HomeRecyclerItem] objects to a [List] of database targeted [ImageItem] objects.
     */
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