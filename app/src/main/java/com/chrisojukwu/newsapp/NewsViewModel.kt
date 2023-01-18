package com.chrisojukwu.newsapp

import androidx.lifecycle.*
import com.chrisojukwu.newsapp.data.NewsRepository
import com.chrisojukwu.newsapp.data.models.NewsItem
import com.chrisojukwu.newsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

enum class NewsApiStatus { LOADING, DONE, ERROR }

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _status = MutableLiveData<NewsApiStatus>()
    val status: LiveData<NewsApiStatus> = _status

    private val _newsList = MutableLiveData<List<NewsItem>?>()
    val newsList: LiveData<List<NewsItem>?> = _newsList


    private val _statusSports = MutableLiveData<NewsApiStatus>()
    val statusSports: LiveData<NewsApiStatus> = _statusSports

    private val _newsListSports = MutableLiveData<List<NewsItem>?>()
    val newsListSports: LiveData<List<NewsItem>?> = _newsListSports

    private val _newsListTech = MutableLiveData<List<NewsItem>?>()
    val newsListTech: LiveData<List<NewsItem>?> = _newsListTech

    private val _statusTech = MutableLiveData<NewsApiStatus>()
    val statusTech: LiveData<NewsApiStatus> = _statusTech

    private val _statusSearch = MutableLiveData<NewsApiStatus>()
    val statusSearch: LiveData<NewsApiStatus> = _statusSearch

    private val _newsListSearch = MutableLiveData<List<NewsItem>?>()
    val newsListSearch: LiveData<List<NewsItem>?> = _newsListSearch


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
        _status.value = NewsApiStatus.LOADING
        repository.getNews()
            .onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _status.value = NewsApiStatus.DONE
                        _newsList.value = result.data
                    }
                    is Resource.Error -> _status.value = NewsApiStatus.ERROR
                    else -> {}
                }
            }.launchIn(viewModelScope)
    }

    //sports
    private fun getNewsSports() {
        _statusSports.value = NewsApiStatus.LOADING
        repository.getNewsSports()
            .onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _statusSports.value = NewsApiStatus.DONE
                        _newsListSports.value = result.data
                    }
                    is Resource.Error -> _statusSports.value = NewsApiStatus.ERROR
                    else -> {}
                }
            }.launchIn(viewModelScope)
    }

    //technology
    private fun getNewsTech() {
        _statusTech.value = NewsApiStatus.LOADING
        repository.getNewsTech()
            .onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _statusTech.value = NewsApiStatus.DONE
                        _newsListTech.value = result.data
                    }
                    is Resource.Error -> _statusTech.value = NewsApiStatus.ERROR
                    else -> {}
                }
            }.launchIn(viewModelScope)
    }

    fun getNewsSearch(query: String) {
        _statusSearch.value = NewsApiStatus.LOADING
        repository.getNewsSearch(query)
            .onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _statusSearch.value = NewsApiStatus.DONE
                        _newsListSearch.value = result.data
                    }
                    is Resource.Error -> _statusSearch.value = NewsApiStatus.ERROR
                    else -> {}
                }
            }.launchIn(viewModelScope)
    }
}