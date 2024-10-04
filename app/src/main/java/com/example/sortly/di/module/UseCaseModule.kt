package com.example.sortly.di.module

import com.example.sortly.data.repository.AppRepository
import com.example.sortly.domain.GetItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideRatesUseCase(repository: AppRepository): GetItemsUseCase =
        GetItemsUseCase(repository)
}