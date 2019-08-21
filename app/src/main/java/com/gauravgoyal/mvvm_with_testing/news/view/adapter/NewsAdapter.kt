package com.gauravgoyal.mvvm_with_testing.news.view.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import com.gauravgoyal.mvvm_with_testing.R
import com.gauravgoyal.mvvm_with_testing.news.modle.Article
import com.gauravgoyal.mvvm_with_testing.news.view.callback.OnClickCallback
import com.gauravgoyal.mvvm_with_testing.utility.DateUtils
import kotlinx.android.synthetic.main.news_list_item.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    internal var articleList: List<Article>? = null
    var callBackItem: OnClickCallback? = null

    fun setCallBack(callBack: OnClickCallback){
        callBackItem = callBack
    }

    fun setProjectList(articleList: List<Article>) {
        if (this.articleList == null) {
            this.articleList = articleList
            notifyItemRangeInserted(0, articleList.size)
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return this@NewsAdapter.articleList!!.size
                }

                override fun getNewListSize(): Int {
                    return articleList.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return this@NewsAdapter.articleList!![oldItemPosition].url === this@NewsAdapter.articleList!![newItemPosition].url
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val newArticle = articleList[newItemPosition]
                    val oldArticle = articleList[oldItemPosition]
                    return newArticle.url == oldArticle.url && oldArticle.author == newArticle.author
                }
            })
            this.articleList = articleList
            result.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {

        return ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.onBindView(articleList!![position],callBackItem!!)

    }

    override fun getItemCount(): Int {
        return if (articleList == null) 0 else articleList!!.size
    }

    class ArticleViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val txtTitle: TextView = view.title
        val txtAuthor: TextView = view.author
        val txtSource: TextView = view.source
        val txtPublishedAt: TextView = view.publishedAt
        val itemContainer: LinearLayout = view.item_container

        fun onBindView(artic :Article, callBack: OnClickCallback){
            txtTitle.text = artic.title
            txtAuthor.text = artic.author
            txtSource.text = artic.description
            txtPublishedAt.text = DateUtils.convertToDateString(artic.publishedAt.toString())
            itemContainer.setOnClickListener {
                callBack.onClick(artic)
            }

        }
    }
}
