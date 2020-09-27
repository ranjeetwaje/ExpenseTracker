package com.ranjeetwaje.wealthmanagement.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ranjeetwaje.wealthmanagement.R
/**
 * A simple [Fragment] subclass.
 * Use the [LandingPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LandingPageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_landing_page, container, false)
    }
}