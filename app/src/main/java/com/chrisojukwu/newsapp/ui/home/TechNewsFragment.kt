package com.chrisojukwu.newsapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.chrisojukwu.newsapp.NewsViewModel
import com.chrisojukwu.newsapp.R
import com.chrisojukwu.newsapp.data.models.NewsItem
import com.chrisojukwu.newsapp.databinding.FragmentTechNewsBinding
import com.chrisojukwu.newsapp.ui.home.TechListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TechFragment : Fragment() {
    private val sharedViewModel: NewsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTechNewsBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        // Giving the binding access to the sharedViewModel
        binding.sharedViewModel = sharedViewModel

        binding.recyclerViewTech.adapter =
            TechListAdapter { newsItem -> onTechNewsItemClick(newsItem) }

        return  binding.root
    }

    private fun onTechNewsItemClick(newsItem: NewsItem) {
        sharedViewModel.currentTechTitle = newsItem.title
        sharedViewModel.currentTechPubDate = newsItem.pubDate.toString()
        sharedViewModel.currentTechBody = newsItem.content.toString()
        var authorString = ""
        if (newsItem.creator != null) {
            for (creator in newsItem.creator) {
                authorString += "$creator "
            }
        }
        sharedViewModel.currentTechAuthors = authorString
        findNavController().navigate(R.id.action_containerFragment_to_fullNewsPageTechFragment)

    }



}