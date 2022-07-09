package com.example.newsapp.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.data.Constants
import com.example.domain.usecases.GetArticlesUseCase
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.mapper.toPresentation
import com.example.newsapp.model.ArticlePresentation
import com.example.newsapp.model.DataResource
import com.example.newsapp.model.ErrorResponse
import com.example.newsapp.utils.ExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase
) : BaseViewModel() {

    private val _newsLiveData =
        MutableLiveData<DataResource<List<ArticlePresentation>>>()

    val newsLiveData: LiveData<DataResource<List<ArticlePresentation>>>
        get() = _newsLiveData

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val messageID = ExceptionHandler.parse(exception)
        _newsLiveData.value =
            DataResource.Error(errorResponse = ErrorResponse(messageID))
    }

    fun getArticles() {
        launchCoroutine {

            val firstNewsSource = async { getArticlesUseCase(Constants.NEXT_WEB_SOURCE) }
            val secondNewsSource = async { getArticlesUseCase(Constants.ASSOCIATED_PRESS_SOURCE) }

            _newsLiveData.value = DataResource.Success(
                firstNewsSource.await().map { it.toPresentation() }
                     +
                     secondNewsSource.await().map { it.toPresentation() }
            )
        }
    }

}