package com.example.newsapp.viewmodel

import com.example.data.Constants
import com.example.domain.usecases.GetArticlesUseCase
import com.example.newsapp.BaseViewModelTest
import com.example.newsapp.mapper.toPresentation
import com.example.newsapp.news.NewsViewModel
import com.example.newsapp.utils.Data
import com.example.newsapp.utils.observeOnce
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class NewsViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: NewsViewModel

    @Mock
    private lateinit var getNewUseCase: GetArticlesUseCase

    @Before
    override fun setup() {
        super.setup()
        viewModel = NewsViewModel(
            getNewUseCase
        )
    }

    @Test
    fun `call getArticles then return articles result with two sources list`() {
        coroutineTestRule.dispatcher.runBlockingTest {


            Mockito.`when`(getNewUseCase(Constants.NEXT_WEB_SOURCE)).thenReturn(
                Data.articlesNextWebSource
            )
            Mockito.`when`(getNewUseCase(Constants.ASSOCIATED_PRESS_SOURCE)).thenReturn(
                Data.articlesAssociatedPress
            )

            viewModel.getArticles()

            Mockito.verify(getNewUseCase, Mockito.times(1)).invoke(Constants.NEXT_WEB_SOURCE)
            Mockito.verify(getNewUseCase, Mockito.times(1))
                .invoke(Constants.ASSOCIATED_PRESS_SOURCE)

            val expectedResult = Data.articlesNextWebSource.map { it.toPresentation() } + Data.articlesAssociatedPress.map { it.toPresentation() }

            viewModel.newsLiveData.observeOnce {
                Truth.assertThat(it.data).isNotNull()
                Truth.assertThat(it.data?.size).isEqualTo(expectedResult.size)
                Truth.assertThat(it.data).isEqualTo(expectedResult)
            }
        }
    }
}