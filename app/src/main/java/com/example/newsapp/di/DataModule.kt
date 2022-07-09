package com.example.newsapp.di

import com.example.data.repository.NewsRepositoryImp
import com.example.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindNewsRepository(newsRepository: NewsRepositoryImp): NewsRepository

}