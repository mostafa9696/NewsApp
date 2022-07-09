package com.example.newsapp.mapper

import com.example.domain.models.Article
import com.example.newsapp.model.ArticlePresentation
import com.example.newsapp.utils.formatDate

internal fun Article.toPresentation(): ArticlePresentation {
    return ArticlePresentation(
        author,
        title,
        description,
        url,
        urlToImage,
        publishedAt?.formatDate()
    )
}