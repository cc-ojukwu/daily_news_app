package com.chrisojukwu.newsapp

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val results: MutableList<NewsItem>,
    val nextPage: Int?
)
