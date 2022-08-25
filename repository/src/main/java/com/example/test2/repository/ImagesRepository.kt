package com.example.test2.repository

import com.example.test2.repository.domains.ApiResponse

class ImagesRepository(private val imagesApi: ImagesApi) {

    suspend fun getApiResponse(keyword: String): ApiResponse =
        imagesApi.getApiResponse(
            apiKey = BuildConfig.API_KEY,
            keyword = keyword
        )

}