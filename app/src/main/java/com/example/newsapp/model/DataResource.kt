package com.example.newsapp.model

sealed class DataResource<T>(
    val data: T? = null,
    val error: String? =null
) {
    class Success<T>(data: T?): DataResource<T>(data)

    class Error<T>(errorMessage: String?) : DataResource<T>(error = errorMessage)

    class Loading<T> : DataResource<T>()

}