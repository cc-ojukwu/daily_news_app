package com.chrisojukwu.newsapp

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val ARG_OBJECT = "object"

class FragmentContainerAdapter( fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    //Initialize list of fragments to be added to the viewPager
    private val fragmentList: List<Fragment> = listOf( HeadlinesFragment(), SportsFragment(), TechFragment())

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        return fragmentList[position]
    }
}