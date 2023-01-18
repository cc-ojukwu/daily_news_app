package com.chrisojukwu.newsapp.data

import com.chrisojukwu.newsapp.data.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApiService {
    //get response data objects for the Headlines Fragment
    @GET("/api/1/news?")
    suspend fun getNewsResponse(
        @Query("apikey") apikey: String,
        @Query("language") language: String
    ): Response<NewsResponse>

    //get response data objects for the Sports Fragment
    @GET("/api/1/news?")
    suspend fun getNewsResponseSports(
        @Query("apikey") apikey: String,
        @Query("category") category: String
    ): Response<NewsResponse>

    //get response data objects for the Tech Fragment
    @GET("/api/1/news?")
    suspend fun getNewsResponseTech(
        @Query("apikey") apikey: String,
        @Query("category") category: String
    ): Response<NewsResponse>

    //get response data objects for the Search Fragment
    @GET("/api/1/news?")
    suspend fun getNewsResponseSearch(
        @Query("apikey") apikey: String,
        @Query("language") language: String,
        @Query("q") q: String
    ): Response<NewsResponse>
}
