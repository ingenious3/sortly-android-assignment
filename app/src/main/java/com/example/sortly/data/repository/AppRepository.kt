package com.example.sortly.data.repository

import com.example.sortly.data.datasource.network.ResultData
import com.example.sortly.data.model.ApiResponse
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    suspend fun fetchItems(currentPage: Int): Flow<ResultData<ApiResponse>>
}