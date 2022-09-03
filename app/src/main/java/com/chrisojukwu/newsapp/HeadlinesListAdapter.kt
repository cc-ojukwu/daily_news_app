package com.chrisojukwu.newsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chrisojukwu.newsapp.databinding.NewsListItemBinding


class HeadlinesListAdapter(private val onNewsItemClick: (newsStory: NewsItem) -> Unit) :
    ListAdapter<NewsItem, HeadlinesListAdapter.NewsItemViewHolder>(DiffCallback) {

    //compare consecutive list items
    companion object DiffCallback : DiffUtil.ItemCallback<NewsItem>() {
        override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem.title == newItem.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val view = NewsListItemBinding.inflate(LayoutInflater.from(parent.context))
        return NewsItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val newsItem = getItem(position)
        holder.bind(newsItem)
        //set onClickListener for recycler view items
        holder.binding.cardViewNewsItem.setOnClickListener {
            onNewsItemClick(newsItem)
        }
    }

    class NewsItemViewHolder(
        var binding: NewsListItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(newsItem: NewsItem) {
            binding.newsItem = newsItem
        }

    }
}

