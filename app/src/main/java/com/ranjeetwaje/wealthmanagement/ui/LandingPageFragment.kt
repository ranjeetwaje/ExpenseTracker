package com.ranjeetwaje.wealthmanagement.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.ranjeetwaje.wealthmanagement.R
import com.ranjeetwaje.wealthmanagement.databinding.FragmentLandingPageBinding

/**
 * A simple [Fragment] subclass.
 * Use the [LandingPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LandingPageFragment(selectedTab: String) : Fragment() {

    private lateinit var binding: FragmentLandingPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_landing_page, container, false)

        binding.btnTotalExpense.setOnClickListener {
//            it.findNavController().navigate(R.id.action_landingPageFragment_to_homeFragment)
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.homeFragment)
        }


        return binding.root
    }
}