package com.example.newsapiproject.data.repository

import com.example.newsapiproject.data.model.APIResponse
import com.example.newsapiproject.data.model.Article
import com.example.newsapiproject.data.repository.dataSource.NewsRemoteDataSource
import com.example.newsapiproject.data.util.Resource
import com.example.newsapiproject.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource
):NewsRepository {
    override suspend fun getNewsHeadLines(country:String, page:Int): Resource<APIResponse> {
        return responseToResource(newsRemoteDataSource.getTopHeadlines(country, page))
    }
    private fun responseToResource(response: Response<APIResponse>):Resource<APIResponse>{
        if(response.isSuccessful){
            response.body()?.let { result->
                return Resource.Success(result)

            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getSearchedNewsHeadLines(searchQuery: String): Resource<APIResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun saveNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }
}