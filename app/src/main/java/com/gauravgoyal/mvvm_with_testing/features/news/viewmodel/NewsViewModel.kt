package com.gauravgoyal.mvvm_with_testing.features.news.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.gauravgoyal.mvvm_with_testing.features.news.model.News
import com.gauravgoyal.mvvm_with_testing.features.news.service.repository.NewsRepository

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    val observableProject: LiveData<News>
    var oldNews: News? = null

    init {
        // a differnt source can be passed, here i am passing techcrunch
        observableProject = NewsRepository.instance.getNews("techcrunch")
    }

    fun setNews(news: News) {
        this.oldNews = news
    }

    /**
     * A creator is used to inject the project ID into the ViewModel
     */
    class Factory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NewsViewModel(application) as T
        }
    }
}
