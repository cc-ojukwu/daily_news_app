package com.chrisojukwu.newsapp

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL =
    "https://newsdata.io"

//use moshi library to parse json into kotlin classes
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

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

//use retrofit object to retrieve REST API response
object NewsApi {
    val retrofitService: NewsApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()
            .create(NewsApiService::class.java)
    }
}