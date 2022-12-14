package com.chrisojukwu.newsapp

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load

//use coil library to display image urls in image view
@BindingAdapter("imageViewUrl")
fun bindImage(imgView: ImageView, img_url: String?) {
    img_url?.let {
        val imgUri = img_url.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("newsApiStatus")
fun bindStatus(statusImageView: ImageView, status: NewsApiStatus?) {
    when (status) {
        NewsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        NewsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        NewsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

//pass API response data to adapter for Headlines Fragment
@BindingAdapter("newsItemList")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<NewsItem>?) {
    val adapter = recyclerView.adapter as HeadlinesListAdapter
    adapter.submitList(data)
}

//pass API response data to adapter for Sports Fragment
@BindingAdapter("sportsNewsItemList")
fun bindSportsRecyclerView(recyclerView: RecyclerView, data: List<NewsItem>?) {
    val adapter = recyclerView.adapter as SportsListAdapter
    adapter.submitList(data)
}

//pass API response data to adapter for Tech Fragment
@BindingAdapter("techNewsItemList")
fun bindTechRecyclerView(recyclerView: RecyclerView, data: List<NewsItem>?) {
    val adapter = recyclerView.adapter as TechListAdapter
    adapter.submitList(data)
}

//pass API response data to adapter for Search Fragment
@BindingAdapter("searchNewsItemList")
fun bindSearchRecyclerView(recyclerView: RecyclerView, data: List<NewsItem>?) {
    val adapter = recyclerView.adapter as SearchListAdapter
    adapter.submitList(data)
}


