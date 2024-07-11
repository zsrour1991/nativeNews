package com.example.newsapiproject.data.repository

import com.example.newsapiproject.data.model.APIResponse
import com.example.newsapiproject.data.model.Article
import com.example.newsapiproject.data.repository.dataSource.NewsLocalDataSource
import com.example.newsapiproject.data.repository.dataSource.NewsRemoteDataSource
import com.example.newsapiproject.data.util.Resource
import com.example.newsapiproject.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
):NewsRepository {
    override suspend fun getNewsHeadLines(country:String, page:Int): Resource<APIResponse> {
        return responseToResource(newsRemoteDataSource.getTopHeadlines(country, page))
    }

    override suspend fun getSearchedNewsHeadLines(
        country: String,
        searchQuery: String,
        page: Int
    ): Resource<APIResponse> {
        return responseToResource(newsRemoteDataSource.getearchedNews(country, searchQuery, page))
    }

    private fun responseToResource(response: Response<APIResponse>):Resource<APIResponse>{
        if(response.isSuccessful){
            response.body()?.let { result->
                return Resource.Success(result)

            }
        }
        return Resource.Error(response.message())
    }



    override suspend fun saveNews(article: Article) {
       newsLocalDataSource.saveArticleToDB(article)
    }

    override suspend fun deleteNews(article: Article) {
        newsLocalDataSource.deleteArticlesFromDB(article)
    }

    override fun getSavedNews(): Flow<List<Article>> {
       return newsLocalDataSource.getSavedArticles()
    }
}