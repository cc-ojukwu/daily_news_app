package com.chrisojukwu.newsapp.di

import android.app.Application
import android.content.Context
import com.chrisojukwu.newsapp.data.NewsRepository
import com.chrisojukwu.newsapp.data.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository
}
