package com.chrisojukwu.newsapp

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.io.IOException

enum class NewsApiStatus { LOADING, DONE, ERROR }

class NewsViewModel : ViewModel() {

    private val _status = MutableLiveData<NewsApiStatus>()
    val status: LiveData<NewsApiStatus> = _status

    private val _newsList = MutableLiveData<MutableList<NewsItem>>()
    val newsList: LiveData<MutableList<NewsItem>> = _newsList



    private val _statusSports = MutableLiveData<NewsApiStatus>()
    val statusSports: LiveData<NewsApiStatus> = _statusSports

    private val _newsListSports = MutableLiveData<MutableList<NewsItem>>()
    val newsListSports: LiveData<MutableList<NewsItem>> = _newsListSports



    private val _newsListTech = MutableLiveData<MutableList<NewsItem>>()
    val newsListTech: LiveData<MutableList<NewsItem>> = _newsListTech

    private val _statusTech = MutableLiveData<NewsApiStatus>()
    val statusTech: LiveData<NewsApiStatus> = _statusTech



    private val _statusSearch = MutableLiveData<NewsApiStatus>()
    val statusSearch: LiveData<NewsApiStatus> = _statusSearch

    private val _newsListSearch = MutableLiveData<MutableList<NewsItem>>()
    val newsListSearch: LiveData<MutableList<NewsItem>> = _newsListSearch


    var currentHeadlineTitle = ""
    var currentHeadlineBody = ""
    var currentHeadlinePubDate = ""
    var currentHeadlineAuthors = ""

    var currentSportsTitle = ""
    var currentSportsBody = ""
    var currentSportsPubDate = ""
    var currentSportsAuthors = ""

    var currentTechTitle = ""
    var currentTechBody = ""
    var currentTechPubDate = ""
    var currentTechAuthors = ""

    var currentSearchTitle = ""
    var currentSearchBody = ""
    var currentSearchPubDate = ""
    var currentSearchAuthors = ""
    var searchString = ""


    init {
        getNews()
        getNewsSports()
        getNewsTech()
    }

    private fun getNews() {
        viewModelScope.launch {
            NewsApiStatus.LOADING
            try {
                val response = NewsApi.retrofitService.getNewsResponse(
                    apikey = "pub_102470fab747a7f00be27ca2d1556739c3d3f",
                    language = "en"
                )
                if (response.isSuccessful && response.body() != null) {
                    val rawResponse = response.body()!!.results

                    //initialize _newsList.value
                    _newsList.value = mutableListOf()

                    //remove array items that contain null image urls
                    for (newsItem in rawResponse) {
                        if (newsItem.image_url != null) {
                            _newsList.value?.add(newsItem)
                        }
                    }
                }
                Log.e("success", "Response successful")
                _status.value = NewsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = NewsApiStatus.ERROR
                Log.e("not successful", "Response not successful")
                //_newsList.value = mutableListOf()
            } catch (e: IOException) {
                _status.value = NewsApiStatus.ERROR
                Log.e("not successful", "IOException- perhaps no internet connection")
            }

        }
    }

    private fun getNewsSports() {
        viewModelScope.launch {
            NewsApiStatus.LOADING
            try {
                val response = NewsApi.retrofitService.getNewsResponseSports(
                    apikey = "pub_102470fab747a7f00be27ca2d1556739c3d3f",
                    category = "sports"
                )
                if (response.isSuccessful && response.body() != null) {
                    Log.e("sports success", "Response successful")
                    val rawResponseSports = response.body()!!.results
                    _newsListSports.value = mutableListOf()
                    for (newsItem in rawResponseSports) {
                        if (newsItem.image_url != null) {
                            _newsListSports.value?.add(newsItem)
                        }
                    }
                }
                _statusSports.value = NewsApiStatus.DONE
            } catch (e: Exception) {
                _statusSports.value = NewsApiStatus.ERROR
                Log.e("sports not successful", "Response not successful")
                //_newsList.value = mutableListOf()
            } catch (e: IOException) {
                _statusSports.value = NewsApiStatus.ERROR
                Log.e("sports not successful", "IOException- perhaps no internet connection")
            }

        }
    }

    private fun getNewsTech() {
        viewModelScope.launch {
            NewsApiStatus.LOADING
            try {
                val response = NewsApi.retrofitService.getNewsResponseTech(
                    apikey = "pub_102470fab747a7f00be27ca2d1556739c3d3f",
                    category = "technology"
                )
                if (response.isSuccessful && response.body() != null) {
                    val rawResponseTech = response.body()!!.results
                    _newsListTech.value = mutableListOf()
                    for (newsItem in rawResponseTech) {
                        if (newsItem.image_url != null) {
                            _newsListTech.value?.add(newsItem)
                        }
                    }
                }
                Log.e("success tech", "Response successful")
                _statusTech.value = NewsApiStatus.DONE
            } catch (e: Exception) {
                _statusTech.value = NewsApiStatus.ERROR
                Log.e("not successful tech", "Response not successful")
                //_newsList.value = mutableListOf()
            } catch (e: IOException) {
                _statusTech.value = NewsApiStatus.ERROR
                Log.e("not successful", "IOException- perhaps no internet connection")
            }
//


        }
    }

    fun getNewsSearch() {
        viewModelScope.launch {
            NewsApiStatus.LOADING
            try {
                val response = NewsApi.retrofitService.getNewsResponseSearch(
                    apikey = "pub_102470fab747a7f00be27ca2d1556739c3d3f",
                    language = "en",
                    q = searchString
                )
                if (response.isSuccessful && response.body() != null) {
                    _newsListSearch.value = response.body()!!.results

                }
                Log.e("success tech", "Response successful")
                _statusSearch.value = NewsApiStatus.DONE
            } catch (e: Exception) {
                _statusSearch.value = NewsApiStatus.ERROR
                Log.e("not successful tech", "Response not successful")
                _newsList.value = mutableListOf()
            } catch (e: IOException) {
                _statusSearch.value = NewsApiStatus.ERROR
                Log.e("not successful", "IOException- perhaps no internet connection")
            }
        }
    }
}