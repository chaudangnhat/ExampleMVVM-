package com.gauravgoyal.mvvm_with_testing.features.news.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.gauravgoyal.mvvm_with_testing.R
import com.gauravgoyal.mvvm_with_testing.features.news.model.Article
import com.gauravgoyal.mvvm_with_testing.features.news.view.adapter.NewsAdapter
import com.gauravgoyal.mvvm_with_testing.features.news.view.callback.OnClickCallback
import com.gauravgoyal.mvvm_with_testing.features.news.viewmodel.NewsViewModel
import com.gauravgoyal.mvvm_with_testing.features.newsdetail.view.activity.NewsDetailActivity

class ArticleListFragment : Fragment(), OnClickCallback {

    private var newsAdapter: NewsAdapter? = null
    private var isLoading: Boolean = true
    private var textLoading: TextView? = null

    override
    fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                     savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_news_list, container, false)

        newsAdapter = NewsAdapter()
        newsAdapter!!.setCallBack(this)

        val recyclerView = view.findViewById<RecyclerView>(R.id.project_list)
        recyclerView.adapter = newsAdapter

        textLoading = view.findViewById(R.id.loading_projects)

        return view
    }

    override
    fun onClick(article: Article) {
        val i = Intent(context, NewsDetailActivity::class.java)
        i.putExtra("url", article.url)
        context!!.startActivity(i)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = NewsViewModel.Factory(
                activity!!.application)

        val viewModel = ViewModelProviders.of(this, factory)
                .get(NewsViewModel::class.java)

        isLoading = true

        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: NewsViewModel) {
        // Update the list when the data changes
        viewModel.observableProject.observe(this, Observer { news ->
            if (news != null) {
                textLoading!!.visibility = GONE
                newsAdapter!!.setProjectList(news.articles!!)
            }
        })
    }

}
