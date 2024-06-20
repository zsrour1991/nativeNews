package com.example.newsapiproject.presentation.di

import android.app.Application
import com.example.newsapiproject.domain.usecase.GetNewsHeadlinesUseCase
import com.example.newsapiproject.presentation.viewModel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
    fun provideNewsViewModelFactory(
        application: Application,
        getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase
    ):NewsViewModelFactory{
        return NewsViewModelFactory(application,getNewsHeadlinesUseCase)

    }
}