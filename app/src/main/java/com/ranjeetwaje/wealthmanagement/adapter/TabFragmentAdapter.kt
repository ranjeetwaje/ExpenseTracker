package com.ranjeetwaje.wealthmanagement.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ranjeetwaje.wealthmanagement.ui.LandingPageFragment

internal class TabFragmentAdapter (
    var context: Context,
    fm: FragmentManager,
    var totalTabs: Int
): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                LandingPageFragment("Month")
            }
            1 -> {
                LandingPageFragment("Week")
            }
            2 -> {
                LandingPageFragment("Day")
            }
            else -> getItem(position)
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }

}