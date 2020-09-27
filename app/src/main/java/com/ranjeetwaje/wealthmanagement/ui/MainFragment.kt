package com.ranjeetwaje.wealthmanagement.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.ranjeetwaje.wealthmanagement.R
import com.ranjeetwaje.wealthmanagement.adapter.TabFragmentAdapter


/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class MainFragment : Fragment() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_main, container, false)
        tabLayout = view.findViewById(R.id.tabLayout)
        viewPager = view.findViewById(R.id.viewPager)
        tabLayout.addTab(tabLayout.newTab().setText("Month"))
        tabLayout.addTab(tabLayout.newTab().setText("Week"))
        tabLayout.addTab(tabLayout.newTab().setText("Day"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = TabFragmentAdapter(this.requireContext(), this.fragmentManager!!,
            tabLayout.tabCount)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        return view
    }
}