package com.example.newsapp.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemArticleBinding
import com.example.newsapp.model.ArticlePresentation

class ArticlesAdapter(
    var onItemClick: (item: ArticlePresentation) -> Unit
) :
    RecyclerView.Adapter<ArticlesAdapter.NewsViewHolder>() {

    var articles: List<ArticlePresentation> = ArrayList()

    fun setArticlesData(articles: List<ArticlePresentation>) {
        this.articles = articles
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(var binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: ArticlePresentation) {
            binding.apply {
                Glide.with(binding.root)
                    .load(article.imageUrl)
                    .placeholder(R.drawable.placeholder)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivArticle)
                tvName.text = article.title
                tvAuthor.text = "by ${article.authorName}"
                tvDate.text = article.publishedAt

                root.setOnClickListener {
                    onItemClick.invoke(articles[adapterPosition])
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticlesAdapter.NewsViewHolder {
        val binding =
            ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticlesAdapter.NewsViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}