package com.example.newsapiproject.presentation.di

import com.example.newsapiproject.domain.repository.NewsRepository
import com.example.newsapiproject.domain.usecase.GetNewsHeadlinesUseCase
import com.example.newsapiproject.domain.usecase.GetSearchedNewsUseCase
import com.example.newsapiproject.domain.usecase.SaveNewsUseCase
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
    fun provideGetNewsheadLinesUseCase(newsRepository: NewsRepository):GetNewsHeadlinesUseCase{
        return GetNewsHeadlinesUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideSearchedNewsUseCase(newsRepository: NewsRepository):GetSearchedNewsUseCase{
        return GetSearchedNewsUseCase(newsRepository)
    }
    @Singleton
    @Provides
    fun provideSavedNewsUseCase(newsRepository: NewsRepository):SaveNewsUseCase{
        return SaveNewsUseCase(newsRepository)
    }
}