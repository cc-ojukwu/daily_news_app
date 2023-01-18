package com.chrisojukwu.newsapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chrisojukwu.newsapp.data.models.NewsItem
import com.chrisojukwu.newsapp.databinding.NewsListItemTechBinding


class TechListAdapter(private val onTechNewsItemClick: (newsStory: NewsItem) -> Unit) :
    ListAdapter<NewsItem, TechListAdapter.NewsItemViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<NewsItem>() {
        override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem.title == newItem.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val view = NewsListItemTechBinding.inflate(LayoutInflater.from(parent.context))
        return NewsItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val newsItem = getItem(position)
        holder.bind(newsItem)
        holder.binding.cardViewTechNewsItem.setOnClickListener {
            onTechNewsItemClick(newsItem)
        }
    }

    class NewsItemViewHolder(
        var binding: NewsListItemTechBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(newsItem: NewsItem) {
            binding.newsItem = newsItem
        }

    }
}

