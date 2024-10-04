package com.example.sortly.domain

import com.example.sortly.data.datasource.network.ResultData
import com.example.sortly.data.model.ApiResponse
import com.example.sortly.data.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(private val repository: AppRepository) {
    suspend operator fun invoke(currentPage: Int): Flow<ResultData<ApiResponse>> {
            return repository.fetchItems(currentPage)
    }
}