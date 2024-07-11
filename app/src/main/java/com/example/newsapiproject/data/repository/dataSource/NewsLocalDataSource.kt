package com.example.newsapiproject.data.repository.dataSource

import com.example.newsapiproject.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {
    suspend fun saveArticleToDB(article: Article)
    fun getSavedArticles():Flow<List<Article>>
    suspend fun deleteArticlesFromDB(article: Article)
}