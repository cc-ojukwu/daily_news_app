package com.chrisojukwu.newsapp

import com.squareup.moshi.Json

data class NewsItem(
    val title: String,
    @Json(name = "link")val id: String,
    val keywords: List<String>?,
    val creator: MutableList<String>?,
    val video_url: String?,
    val description: String?,
    val content: String?,
    val pubDate: String?,
    val image_url: String?,
    val source_id: String?,
    val country: List<String>?,
    val category: List<String>?,
    val language: String?


)