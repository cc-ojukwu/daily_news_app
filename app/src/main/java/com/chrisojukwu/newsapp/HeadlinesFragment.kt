package com.chrisojukwu.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.chrisojukwu.newsapp.databinding.FragmentHeadlinesBinding

class HeadlinesFragment : Fragment() {

    private val sharedViewModel: NewsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHeadlinesBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = viewLifecycleOwner

        // Giving the binding access to the OverviewViewModel
        binding.sharedViewModel = sharedViewModel

        binding.recyclerViewHeadlines.adapter =
            HeadlinesListAdapter { newsItem -> onNewsItemClick(newsItem) }

        binding.recyclerViewHeadlines.addItemDecoration(
            SimpleDividerItemDecoration(
                requireContext(),
                R.drawable.line_divider
            )
        )
        return binding.root
    }

    private fun onNewsItemClick(newsItem: NewsItem) {
        //update viewModel data
        sharedViewModel.currentHeadlineTitle = newsItem.title
        sharedViewModel.currentHeadlinePubDate = newsItem.pubDate.toString()
        sharedViewModel.currentHeadlineBody = newsItem.content.toString()
        var authorString = ""
        if (newsItem.creator != null) {
            for (creator in newsItem.creator) {
                authorString += "$creator "
            }
        }
        sharedViewModel.currentHeadlineAuthors = authorString

        // navigate to full news page
        findNavController().navigate(R.id.action_containerFragment_to_completeNewsFragment)
    }

}