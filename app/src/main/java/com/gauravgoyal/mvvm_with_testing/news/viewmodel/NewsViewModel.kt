package com.gauravgoyal.mvvm_with_testing.news.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.databinding.ObservableField

import com.gauravgoyal.mvvm_with_testing.news.model.News
import com.gauravgoyal.mvvm_with_testing.news.service.repository.NewsRepository

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    val observableProject: LiveData<News>
    var news = ObservableField<News>()

    init {
        // a differnt source can be passed, here i am passing techcrunch
        observableProject = NewsRepository.instance.getNews("techcrunch")
    }

    fun setNews(news: News) {
        this.news.set(news)
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
