package com.example.data.models

data class NewsResponse(
    val articles: List<ArticleResponse>
)

data class ArticleResponse(
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null
)