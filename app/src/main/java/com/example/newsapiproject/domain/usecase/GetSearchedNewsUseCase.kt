package com.example.newsapiproject.domain.usecase

import com.example.newsapiproject.data.model.APIResponse
import com.example.newsapiproject.data.util.Resource
import com.example.newsapiproject.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(searchQuery:String):Resource<APIResponse>{
        return newsRepository.getSearchedNewsHeadLines(searchQuery)
    }
}