package com.example.newsapiproject.data.repository.dataSourceImpl

import com.example.newsapiproject.data.api.NewsApiService
import com.example.newsapiproject.data.model.APIResponse
import com.example.newsapiproject.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImp(
    private val newsAPIService: NewsApiService,

):NewsRemoteDataSource {
    override suspend fun getTopHeadlines(country:String, page:Int): Response<APIResponse> {
      return newsAPIService.getTopHeadlines(country, page)
    }
}