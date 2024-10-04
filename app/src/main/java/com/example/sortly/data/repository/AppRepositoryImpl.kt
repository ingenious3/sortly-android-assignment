package com.example.sortly.data.repository

import com.example.sortly.data.datasource.RemoteDataSource
import com.example.sortly.data.datasource.network.ResultData
import com.example.sortly.data.model.ApiResponse
import com.example.sortly.data.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource):
    AppRepository {

    override suspend fun fetchItems(currentPage: Int): Flow<ResultData<ApiResponse>> {

        return flow {
            emit(ResultData.Loading())
            when (val result = remoteDataSource.fetchItems(currentPage)) {
                is ResultData.Success -> {
                    emit(ResultData.Success(result.data))
                }
                is ResultData.Failure -> {
                    emit(ResultData.Failure(errorCode = result.errorCode, msg = result.msg))
                }
                is ResultData.Exception -> {
                    emit(ResultData.Exception(throwable = result.throwable))
                }
                is ResultData.Loading -> {
                    emit(ResultData.Loading())
                }
            }
        }.flowOn(Dispatchers.IO)
    }

}