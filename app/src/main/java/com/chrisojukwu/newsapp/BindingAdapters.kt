package com.chrisojukwu.newsapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.chrisojukwu.newsapp.data.models.NewsItem
import com.chrisojukwu.newsapp.ui.home.HeadlinesListAdapter
import com.chrisojukwu.newsapp.ui.home.SportsListAdapter
import com.chrisojukwu.newsapp.ui.home.TechListAdapter
import com.chrisojukwu.newsapp.ui.search.SearchListAdapter

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

@BindingAdapter("newsApiRefresh")
fun showRefresh(textView: TextView, status: NewsApiStatus?) {
    when (status) {
        NewsApiStatus.ERROR -> {
            textView.visibility = View.VISIBLE
        }
        NewsApiStatus.DONE -> {
            textView.visibility = View.GONE
        }
        NewsApiStatus.LOADING -> {
            textView.visibility = View.GONE
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


