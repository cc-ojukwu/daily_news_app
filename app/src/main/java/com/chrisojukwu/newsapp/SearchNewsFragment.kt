package com.chrisojukwu.newsapp

import android.content.Context.*
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.chrisojukwu.newsapp.databinding.FragmentSearchNewsBinding

class SearchNewsFragment : Fragment() {

    private val sharedViewModel: NewsViewModel by activityViewModels()
    private lateinit var binding: FragmentSearchNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Set a custom view for the ActionBar
        (activity as AppCompatActivity?)!!.supportActionBar!!.displayOptions =
            androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM
        (activity as AppCompatActivity?)!!.supportActionBar!!.setCustomView(R.layout.action_bar_custom)

        // Inflate the layout for this fragment
        binding = FragmentSearchNewsBinding.inflate(inflater)


        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = viewLifecycleOwner

        // Giving the binding access to the sharedViewModel
        binding.sharedViewModel = sharedViewModel


        binding.recyclerViewSearch.adapter =
            SearchListAdapter { newsItem -> onSearchNewsItemClick(newsItem) }

        binding.recyclerViewSearch.addItemDecoration(
            SimpleDividerItemDecoration(
                requireContext(),
                R.drawable.line_divider
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get search text from editText view and request data from API
        binding.editTextSearch.setOnEditorActionListener {v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                sharedViewModel.searchString = v.text.toString()
                hideKeyboard()
                sharedViewModel.getNewsSearch()
                true
            } else{
                false
            }
        }
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    //persist data across fragments using sharedViewModel
    private fun onSearchNewsItemClick(newsItem: NewsItem) {
        sharedViewModel.currentSearchTitle = newsItem.title
        sharedViewModel.currentSearchPubDate = newsItem.pubDate.toString()
        sharedViewModel.currentSearchBody = newsItem.content.toString()
        var authorString = ""
        if (newsItem.creator != null) {
            for (creator in newsItem.creator) {
                authorString += "$creator "
            }
        }
        sharedViewModel.currentSearchAuthors = authorString
        findNavController().navigate(R.id.action_searchNewsFragment_to_full_news_page_search)
    }

}