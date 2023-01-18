package com.chrisojukwu.newsapp.data

import com.chrisojukwu.newsapp.data.models.NewsItem
import com.chrisojukwu.newsapp.data.models.NewsResponse
import com.chrisojukwu.newsapp.util.Resource
import kotlinx.coroutines.flow.Flow


interface NewsRepository {

    fun getNews(): Flow<Resource<List<NewsItem>>>

    fun getNewsSports(): Flow<Resource<List<NewsItem>>>

    fun getNewsTech(): Flow<Resource<List<NewsItem>>>

    fun getNewsSearch(query: String): Flow<Resource<List<NewsItem>>>

}