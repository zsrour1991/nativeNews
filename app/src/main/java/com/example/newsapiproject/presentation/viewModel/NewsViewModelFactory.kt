package com.example.newsapiproject.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapiproject.domain.usecase.DeleteSavedNewsUseCase
import com.example.newsapiproject.domain.usecase.GetNewsHeadlinesUseCase
import com.example.newsapiproject.domain.usecase.GetSavedNewsUseCase
import com.example.newsapiproject.domain.usecase.GetSearchedNewsUseCase
import com.example.newsapiproject.domain.usecase.SaveNewsUseCase

class NewsViewModelFactory(
    private val app:Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase
) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(
            app,
            getNewsHeadlinesUseCase,
            getSearchedNewsUseCase,
            saveNewsUseCase,
            getSavedNewsUseCase,
            deleteSavedNewsUseCase
        ) as T
    }
}