package com.chrisojukwu.newsapp.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.chrisojukwu.newsapp.NewsViewModel
import com.chrisojukwu.newsapp.databinding.FragmentFullNewsPageSearchBinding

class FullNewsPageSearch : Fragment() {
    private var binding: FragmentFullNewsPageSearchBinding? = null
    private val sharedViewModel: NewsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.displayOptions =
            ActionBar.DISPLAY_HOME_AS_UP
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentFullNewsPageSearchBinding.inflate(inflater)
        binding = fragmentBinding

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding?.lifecycleOwner = viewLifecycleOwner
        binding?.sharedViewModel = sharedViewModel
        
        return fragmentBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}