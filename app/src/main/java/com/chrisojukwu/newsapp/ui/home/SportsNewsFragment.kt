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
import com.chrisojukwu.newsapp.databinding.FragmentSportsNewsBinding
import com.chrisojukwu.newsapp.ui.home.SportsListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SportsFragment : Fragment() {

    private val sharedViewModel: NewsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentSportsNewsBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        // Giving the binding access to the OverviewViewModel
        binding.sharedViewModel = sharedViewModel

        binding.recyclerViewSports.adapter =
            SportsListAdapter { newsItem -> onSportsNewsItemClick(newsItem) }

        return  binding.root
    }

    private fun onSportsNewsItemClick(newsItem: NewsItem) {
        sharedViewModel.currentSportsTitle = newsItem.title
        sharedViewModel.currentSportsPubDate = newsItem.pubDate.toString()
        sharedViewModel.currentSportsBody = newsItem.content.toString()
        var authorString = ""
        if (newsItem.creator != null) {
            for (creator in newsItem.creator) {
                authorString += "$creator "
            }
        }
        sharedViewModel.currentSportsAuthors = authorString
        findNavController().navigate(R.id.action_containerFragment_to_fullNewsPageSportsFragment)

    }

}