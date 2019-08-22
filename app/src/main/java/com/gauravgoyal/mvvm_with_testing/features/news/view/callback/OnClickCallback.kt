package com.gauravgoyal.mvvm_with_testing.features.news.view.callback

import com.gauravgoyal.mvvm_with_testing.features.news.model.Article

interface OnClickCallback {
    fun onClick(article: Article) {}
}
