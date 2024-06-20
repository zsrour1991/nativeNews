package com.example.newsapiproject.domain.usecase

import com.example.newsapiproject.data.model.APIResponse
import com.example.newsapiproject.data.util.Resource
import com.example.newsapiproject.domain.repository.NewsRepository

class GetNewsHeadlinesUseCase(private var newsRepository: NewsRepository) {

    suspend fun execute(country:String,page:Int): Resource<APIResponse>{
        return newsRepository.getNewsHeadLines(country, page)
    }

}