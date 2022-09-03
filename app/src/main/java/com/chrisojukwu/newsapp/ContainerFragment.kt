package com.chrisojukwu.newsapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ContainerFragment : Fragment() {
    private lateinit var fragmentContainerAdapter: FragmentContainerAdapter
    private lateinit var viewPager: ViewPager2
    private var actionBarLayout = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Set a custom view for the ActionBar
        (activity as AppCompatActivity?)!!.supportActionBar!!.displayOptions =
            androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM
        (activity as AppCompatActivity?)!!.supportActionBar!!.setCustomView(R.layout.action_bar_custom)
//        when (context?.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
//            Configuration.UI_MODE_NIGHT_YES -> {
//                (activity as AppCompatActivity?)!!.supportActionBar!!.setCustomView(R.layout.action_bar_custom_night)
//            }
//            Configuration.UI_MODE_NIGHT_NO -> {
//                (activity as AppCompatActivity?)!!.supportActionBar!!.setCustomView(R.layout.action_bar_custom)
//            }
//            Configuration.UI_MODE_NIGHT_UNDEFINED -> {
//                (activity as AppCompatActivity?)!!.supportActionBar!!.setCustomView(R.layout.action_bar_custom)
//            }
//        }


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_container, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentContainerAdapter = FragmentContainerAdapter(this)

        //Implement the viewPager for the Container Fragment
        viewPager = view.findViewById(R.id.view_pager_container)
        viewPager.adapter = fragmentContainerAdapter
        val tabLayout: TabLayout = view.findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "General"
                1 -> tab.text = "Sports"
                2 -> tab.text = "Tech"
            }
        }.attach()
    }
}




