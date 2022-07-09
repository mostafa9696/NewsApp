package com.example.data.repository

import com.example.data.mappers.toDomain
import com.example.data.remote.ApisService
import com.example.domain.models.Article
import com.example.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(
    private val apisService: ApisService
) : NewsRepository {

    override suspend fun getNews(source: String): List<Article> =
        apisService.getNewsArticles(source).articles.map {
            it.toDomain()
        }

}