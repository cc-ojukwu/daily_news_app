package com.chrisojukwu.newsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chrisojukwu.newsapp.databinding.NewsListItemSearchBinding


class SearchListAdapter(private val onSearchNewsItemClick: (newsStory: NewsItem) -> Unit) :
    ListAdapter<NewsItem, SearchListAdapter.NewsItemViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<NewsItem>() {
        override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem.title == newItem.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val view = NewsListItemSearchBinding.inflate(LayoutInflater.from(parent.context))
        return NewsItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val newsItem = getItem(position)
        holder.bind(newsItem)
        holder.binding.textContainer.setOnClickListener {
            onSearchNewsItemClick(newsItem)
        }
    }

    class NewsItemViewHolder(
        var binding: NewsListItemSearchBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(newsItem: NewsItem) {
            binding.newsItem = newsItem
        }

    }
}

