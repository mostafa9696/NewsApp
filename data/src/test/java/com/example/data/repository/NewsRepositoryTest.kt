package com.example.data.repository

import com.example.data.Constants
import com.example.data.utils.BaseTest
import com.example.domain.repository.NewsRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class NewsRepositoryTest : BaseTest() {

    private lateinit var newsRepository: NewsRepository

    @Before
    override fun setup() {
        super.setup()
        newsRepository = NewsRepositoryImp(apiService)
    }

    @Test
    fun `given the_next_web source then return success result`() = runBlocking {
        val articles = newsRepository.getNews(Constants.NEXT_WEB_SOURCE)
        Truth.assertThat(articles).isNotEmpty()
        Truth.assertThat(articles[0].author).isEqualTo("Thomas Macaulay")
    }

    @Test
    fun `given associated-press source then return success result`() = runBlocking {
        val articles = newsRepository.getNews(Constants.ASSOCIATED_PRESS_SOURCE)
        Truth.assertThat(articles).isNotEmpty()
        Truth.assertThat(articles[0].author).isEqualTo("HELENA ALVES and JOSEPH WILSON")
    }
}