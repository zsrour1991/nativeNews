package com.example.newsapiproject.presentation.di

import NewsLocalDataSourceImpl
import com.example.newsapiproject.data.db.ArticleDAO
import com.example.newsapiproject.data.repository.dataSource.NewsLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideLocalDataSource(articleDAO: ArticleDAO):NewsLocalDataSource{
       return NewsLocalDataSourceImpl(articleDAO)
    }
}