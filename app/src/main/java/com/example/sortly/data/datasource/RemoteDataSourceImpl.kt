package com.example.sortly.data.datasource

import com.example.sortly.data.datasource.RemoteDataSource
import com.example.sortly.data.datasource.network.ApiService
import com.example.sortly.data.datasource.network.NetworkConstants.DEFAULT_ITEM_COUNT
import com.example.sortly.data.datasource.network.ResultData
import com.example.sortly.data.model.ApiResponse
import retrofit2.HttpException
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apiService: ApiService):
    RemoteDataSource {

    override suspend fun fetchItems(pageNumber: Int): ResultData<ApiResponse> {
        return try {
            val response = apiService.fetchItems(pageNumber, DEFAULT_ITEM_COUNT)
            if (response.isSuccessful && response.body() != null) {
                ResultData.Success(response.body()!!)
            } else {
                ResultData.Failure(response.code(), response.message())
            }
        } catch (e: HttpException) {
            e.printStackTrace()
            ResultData.Failure(e.code(), e.message())
        } catch (e: Exception) {
            e.printStackTrace()
            ResultData.Exception(e)
        }
    }
}