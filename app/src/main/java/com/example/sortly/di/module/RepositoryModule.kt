package com.example.sortly.di.module

import com.example.sortly.data.datasource.RemoteDataSource
import com.example.sortly.data.repository.AppRepository
import com.example.sortly.data.repository.AppRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesAppRepository(remoteDataSource: RemoteDataSource) : AppRepository {
        return AppRepositoryImpl(remoteDataSource)
    }

}