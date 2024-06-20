package com.example.newsapiproject.domain.usecase

import com.example.newsapiproject.data.model.Article
import com.example.newsapiproject.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {
     fun execute():Flow<List<Article>>{
         return newsRepository.getSavedNews()
     }
}