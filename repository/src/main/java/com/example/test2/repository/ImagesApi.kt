package com.example.test2.repository

import com.example.test2.repository.domains.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesApi {

    @GET("search.json?tbm=isch&ijn=0")
    suspend fun getApiResponse(
        @Query("api_key") apiKey: String,
        @Query("q") keyword: String
    ): ApiResponse

}