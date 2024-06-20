package com.example.newsapiproject.presentation.di

import com.example.newsapiproject.data.api.NewsApiService
import com.example.newsapiproject.data.repository.dataSource.NewsRemoteDataSource
import com.example.newsapiproject.data.repository.dataSourceImpl.NewsRemoteDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)

class RemoteDataModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(newsApiService: NewsApiService):NewsRemoteDataSource{
        return NewsRemoteDataSourceImp(newsApiService)
    }
}