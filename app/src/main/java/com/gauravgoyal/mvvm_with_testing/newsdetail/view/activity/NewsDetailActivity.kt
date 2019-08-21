package com.gauravgoyal.mvvm_with_testing.newsdetail.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient
import com.gauravgoyal.mvvm_with_testing.R
import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetailActivity : AppCompatActivity() {

    private var url : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        url = intent.getStringExtra("url")

        container.getSettings().setJavaScriptEnabled(true) // enable javascript

        container.setWebViewClient(object : WebViewClient() {
            override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {}
        })
        container.loadUrl(url)
    }

}
