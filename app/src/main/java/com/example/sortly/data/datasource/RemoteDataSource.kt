package com.example.sortly.data.datasource

import com.example.sortly.data.datasource.network.ResultData
import com.example.sortly.data.model.ApiResponse

interface RemoteDataSource {
    suspend fun fetchItems(pageNumber: Int): ResultData<ApiResponse>
}