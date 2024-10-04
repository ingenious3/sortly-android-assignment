package com.example.sortly.di.module

import com.example.sortly.data.datasource.RemoteDataSource
import com.example.sortly.data.datasource.RemoteDataSourceImpl
import com.example.sortly.data.datasource.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun providesRemoteDatasource(apiService: ApiService): RemoteDataSource =
        RemoteDataSourceImpl(apiService)
}