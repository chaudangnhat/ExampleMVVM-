package com.gauravgoyal.mvvm_with_testing.news.service.repository

import com.gauravgoyal.mvvm_with_testing.news.model.News
import com.gauravgoyal.mvvm_with_testing.utility.Constants

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

internal interface NewsService {

    @GET("top-headlines")
    fun getNews(@Query("sources") source: String): Call<News>

    companion object {
        val URL = Constants.API_URL
    }
}
