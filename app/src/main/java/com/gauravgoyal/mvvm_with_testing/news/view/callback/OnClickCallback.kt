package com.gauravgoyal.mvvm_with_testing.news.view.callback

import com.gauravgoyal.mvvm_with_testing.news.modle.Article

interface OnClickCallback {
    fun onClick(article: Article) {}
}
