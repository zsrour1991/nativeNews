package com.example.newsapiproject.data.repository.dataSource

import com.example.newsapiproject.data.model.Article

interface NewsLocalDataSource {
    suspend fun saveArticleToDB(article: Article)
}