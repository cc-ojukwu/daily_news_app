package com.chrisojukwu.newsapp.data

import android.util.Log
import com.chrisojukwu.newsapp.BuildConfig
import com.chrisojukwu.newsapp.data.models.NewsItem
import com.chrisojukwu.newsapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: NewsApiService
) : NewsRepository {

    override fun getNews(): Flow<Resource<List<NewsItem>>> = flow {
        try {
            val response = apiService.getNewsResponse(
                BuildConfig.API_KEY,
                language = "en"
            )
            if (response.isSuccessful) {
                Log.d("news", "Response successful")
                val rawResponse = response.body()!!.results

                //remove array items that contain null image urls
                val refinedList = mutableListOf<NewsItem>()
                for (newsItem in rawResponse) {
                    if (newsItem.image_url != null) {
                        refinedList.add(newsItem)
                    }
                }
                emit(Resource.Success(refinedList))
            }
        } catch (e: Exception) {
            Log.d("news", "Response not successful")
            println(e.localizedMessage)
            emit(Resource.Error(e))
        }

    }.flowOn(Dispatchers.IO)

    override fun getNewsSports(): Flow<Resource<List<NewsItem>>> = flow {

        try {
            val response = apiService.getNewsResponseSports(
                BuildConfig.API_KEY,
                category = "sports"
            )
            if (response.isSuccessful) {
                Log.d("sports", "Response successful")
                val rawResponse = response.body()!!.results

                //remove array items that contain null image urls
                val refinedList = mutableListOf<NewsItem>()
                for (newsItem in rawResponse) {
                    if (newsItem.image_url != null) {
                        refinedList.add(newsItem)
                    }
                }
                emit(Resource.Success(refinedList))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }

    }.flowOn(Dispatchers.IO)

    override fun getNewsTech(): Flow<Resource<List<NewsItem>>> = flow {
        try {
            val response = apiService.getNewsResponseTech(
                BuildConfig.API_KEY,
                category = "technology"
            )
            if (response.isSuccessful) {
                Log.d("tech", "Response successful")
                val rawResponse = response.body()!!.results

                //remove array items that contain null image urls
                val refinedList = mutableListOf<NewsItem>()
                for (newsItem in rawResponse) {
                    if (newsItem.image_url != null) {
                        refinedList.add(newsItem)
                    }
                }
                emit(Resource.Success(refinedList))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }

    }.flowOn(Dispatchers.IO)

    override fun getNewsSearch(query: String): Flow<Resource<List<NewsItem>>> = flow {
        try {
            val response = apiService.getNewsResponseSearch(
                BuildConfig.API_KEY,
                language = "en",
                q = query
            )
            if (response.isSuccessful) {
                Log.d("tech", "Response successful")

                emit(Resource.Success(response.body()!!.results))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }.flowOn(Dispatchers.IO)

}