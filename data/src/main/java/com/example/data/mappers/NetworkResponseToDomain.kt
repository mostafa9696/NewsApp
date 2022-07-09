package com.example.data.mappers

import com.example.data.models.ArticleResponse
import com.example.domain.models.Article

internal fun ArticleResponse.toDomain(): Article {
    return Article(
        author,
        title,
        description,
        url,
        urlToImage,
        publishedAt
    )
}