package com.example.newsapiproject.domain.usecase

import com.example.newsapiproject.data.model.APIResponse
import com.example.newsapiproject.data.util.Resource
import com.example.newsapiproject.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(country:String,searchQuery:String,page:Int):Resource<APIResponse>{
        return newsRepository.getSearchedNewsHeadLines(country,searchQuery,page)
    }
}