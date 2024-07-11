package com.example.newsapiproject.presentation.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.newsapiproject.data.model.APIResponse
import com.example.newsapiproject.data.util.Resource
import androidx.lifecycle.viewModelScope
import com.example.newsapiproject.data.model.Article
import com.example.newsapiproject.domain.usecase.DeleteSavedNewsUseCase
import com.example.newsapiproject.domain.usecase.GetNewsHeadlinesUseCase
import com.example.newsapiproject.domain.usecase.GetSavedNewsUseCase
import com.example.newsapiproject.domain.usecase.GetSearchedNewsUseCase
import com.example.newsapiproject.domain.usecase.SaveNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.http.Query


class NewsViewModel(
    private val app:Application,
    val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase
) : AndroidViewModel(app) {
    val newsHeadLines:MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun getNewsHeadLines(country:String,page:Int)=viewModelScope.launch(Dispatchers.IO) {
        newsHeadLines.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {

                val apiResult = getNewsHeadlinesUseCase.execute(country, page)
                newsHeadLines.postValue(apiResult)
            } else {
                newsHeadLines.postValue(Resource.Error("Internet us not available"))
            }
        }catch (e:Exception){
            newsHeadLines.postValue(Resource.Error(e.message.toString()))

        }

    }
    private fun isNetworkAvailable(context: Context):Boolean{
        if(context == null)return false
        val connectivityManager=context.getSystemService(Context.CONNECTIVITY_SERVICE)as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if(capabilities != null){
                when{
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)->{
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)->{
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)->{
                        return true
                    }
                }
            }
        }
return false
    }
    // Search
    val searchedNews: MutableLiveData<Resource<APIResponse>> = MutableLiveData()
    fun searchNews(
        country: String,
        searchQuery: String,
        page: Int
    )= viewModelScope.launch {
        searchedNews.postValue(Resource.Loading())
        try {

        if(isNetworkAvailable(app)){
            val response = getSearchedNewsUseCase.execute(
                country, searchQuery, page
            )
            searchedNews.postValue(response)
        }else{
            searchedNews.postValue(Resource.Error("No internet connection"))
        }}
        catch (e:Exception){
            searchedNews.postValue(Resource.Error(e.message.toString()))

        }
    }
    // local data
    fun saveArticle(article: Article)=viewModelScope.launch {
        saveNewsUseCase.execute(article)

    }
    fun getSavedNews() = liveData {
        getSavedNewsUseCase.execute().collect{
            emit(it)
        }
    }
    fun deleteArticle(article: Article)=viewModelScope.launch {
        deleteSavedNewsUseCase.execute(article)
    }
}