package com.example.newsapiproject.domain.usecase

import com.example.newsapiproject.data.model.Article
import com.example.newsapiproject.domain.repository.NewsRepository

class DeleteSavedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article)=newsRepository.deleteNews(article)
}