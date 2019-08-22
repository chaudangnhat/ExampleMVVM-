package com.gauravgoyal.mvvm_with_testing.features.news.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.gauravgoyal.mvvm_with_testing.R
import com.gauravgoyal.mvvm_with_testing.features.news.model.Article
import com.gauravgoyal.mvvm_with_testing.features.news.view.callback.OnClickCallback
import com.gauravgoyal.mvvm_with_testing.utility.DateUtils
import kotlinx.android.synthetic.main.news_list_item.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    internal var articleList: List<Article>? = null
    private var callBackItem: OnClickCallback? = null

    fun setCallBack(callBack: OnClickCallback){
        this.callBackItem = callBack
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

    class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtTitle: TextView = view.title
        private val txtAuthor: TextView = view.author
        private val txtSource: TextView = view.source
        private val txtPublishedAt: TextView = view.publishedAt
        private val itemContainer: LinearLayout = view.item_container

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
