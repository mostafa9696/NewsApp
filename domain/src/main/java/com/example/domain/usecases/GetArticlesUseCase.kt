package com.example.domain.usecases

import com.example.domain.repository.NewsRepository
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(source: String) =
        newsRepository.getNews(source)
}