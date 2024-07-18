package com.example.newsapiproject.domain.repository

import androidx.lifecycle.LiveData
import com.example.newsapiproject.data.model.APIResponse
import com.example.newsapiproject.data.model.Article
import com.example.newsapiproject.data.util.Resource
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.*

interface NewsRepository {

    suspend fun getNewsHeadLines(country:String, page:Int): Resource<APIResponse>
    suspend fun getSearchedNewsHeadLines(country:String,searchQuery:String,page: Int): Resource<APIResponse>
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews():Flow<List<Article>>
}