package com.example.domain.repository

import com.example.domain.models.Article

interface NewsRepository {

    suspend fun getNews(source: String): List<Article>
}