package com.chrisojukwu.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.chrisojukwu.newsapp.databinding.FragmentFullNewsPageTechBinding

class FullNewsPageTechFragment : Fragment() {

    private var binding: FragmentFullNewsPageTechBinding? = null
    private val sharedViewModel: NewsViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //display only the up navigate icon in the Action Bar
        (activity as AppCompatActivity?)!!.supportActionBar!!.displayOptions =
            ActionBar.DISPLAY_HOME_AS_UP
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val fragmentBinding = FragmentFullNewsPageTechBinding.inflate(inflater)
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