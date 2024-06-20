package com.example.newsapiproject.api

import com.example.newsapiproject.data.api.NewsApiService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.common.truth.Truth.assertThat // Correct import for Truth assertions

class NewsAPIServiceTest {
    private lateinit var service: NewsApiService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
     server= MockWebServer()
        service=Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }

    private fun enqueueMockResponse(
        fileName: String
    ){
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse= MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }
    @Test
    fun getTopHeadlines_sentRequest_receivedExpected(){
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody=service.getTopHeadlines("us",1).body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isNotEqualTo("v2/top-headlines?country=us&page=1&category=business&apiKey=39d7ee6e36b9492bbc139e2f92c574e0")
        }
    }
    @Test
    fun getTopHeadlines_receivedResponse_correctPageSize(){
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines("us",1).body()
            val articlesList=responseBody!!.articles
            assertThat(articlesList.size).isEqualTo(20)
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctContent(){
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines("us",1).body()
            val articlesList=responseBody!!.articles
           val article=articlesList[0]
            assertThat(article.author).isEqualTo("Reuters")
            assertThat(article.url).isEqualTo("https://www.reuters.com/technology/nvidia-earnings-could-spark-200-billion-swing-shares-options-show-2024-05-21/")
            assertThat(article.publishedAt).isEqualTo("2024-05-21T06:18:58Z")
        }
    }

    @After
    fun tearDown() {
      server.shutdown()
    }
}