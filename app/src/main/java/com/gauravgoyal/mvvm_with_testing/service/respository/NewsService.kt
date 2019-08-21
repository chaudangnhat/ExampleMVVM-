package com.gauravgoyal.mvvm_with_testing.service.respository

import com.gauravgoyal.mvvm_with_testing.news.modle.News
import com.gauravgoyal.mvvm_with_testing.utility.Constants

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

internal interface NewsService {

    @GET("top-headlines")
    fun getNews(@Query("sources") source: String): Call<News>

    companion object {
        val URL = Constants.API_URL
    }
}
