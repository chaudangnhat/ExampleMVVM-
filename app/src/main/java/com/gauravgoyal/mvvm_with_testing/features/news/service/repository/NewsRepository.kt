package com.gauravgoyal.mvvm_with_testing.features.news.service.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gauravgoyal.mvvm_with_testing.BuildConfig
import com.gauravgoyal.mvvm_with_testing.features.news.model.News

import okhttp3.OkHttpClient

import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRepository private constructor() {

    private val newsService: NewsService

    init {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apiKey", BuildConfig.NewsApiKey)
                    .build()

            val request = original.newBuilder()
                    .url(url).build()
            chain.proceed(request)
        }

        val retrofit = Retrofit.Builder()
                .baseUrl(NewsService.URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        newsService = retrofit.create(NewsService::class.java)
    }

    fun getNews(source: String): LiveData<News> {
        val data = MutableLiveData<News>()
        newsService.getNews(source).enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }

    companion object {

        private var projectRepository: NewsRepository? = null

        val instance: NewsRepository
            @Synchronized get() {
                if (projectRepository == null) {
                    if (projectRepository == null) {
                        projectRepository = NewsRepository()
                    }
                }
                return projectRepository as NewsRepository
            }
    }

}
