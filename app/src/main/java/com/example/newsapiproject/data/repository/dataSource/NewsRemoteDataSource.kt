package com.example.newsapiproject.data.repository.dataSource

import android.graphics.pdf.PdfDocument.Page
import com.example.newsapiproject.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.Query

interface NewsRemoteDataSource {
    suspend fun getTopHeadlines(country:String, page: Int):Response<APIResponse>
    suspend fun getearchedNews(country:String,searchQuery: String, page: Int):Response<APIResponse>
}