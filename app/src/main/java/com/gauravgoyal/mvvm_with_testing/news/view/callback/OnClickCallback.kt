package com.gauravgoyal.mvvm_with_testing.news.view.callback

import com.gauravgoyal.mvvm_with_testing.news.model.Article

interface OnClickCallback {
    fun onClick(article: Article) {}
}
