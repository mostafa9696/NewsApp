package com.example.data.remote

import com.example.data.BuildConfig
import com.example.data.models.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApisService {

    @GET("articles")
    suspend fun getNewsArticles(
        @Query("source") source: String,
        @Query("apiKey") clientId: String = BuildConfig.API_KEY
    ): NewsResponse
}