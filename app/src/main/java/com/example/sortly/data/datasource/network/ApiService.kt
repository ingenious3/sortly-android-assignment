package com.example.sortly.data.datasource.network

import com.example.sortly.data.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(NetworkConstants.ENDPOINT)
    suspend fun fetchItems(@Query("page") pageNumber: Int,
                           @Query("per_page") perPage: Int): Response<ApiResponse>

}