package com.ranjeetwaje.wealthmanagement.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.ranjeetwaje.wealthmanagement.R
import com.ranjeetwaje.wealthmanagement.databinding.FragmentSignUpBinding
import com.ranjeetwaje.wealthmanagement.utils.PreferenceHelper
import com.ranjeetwaje.wealthmanagement.viewmodel.SignUpViewModel
import com.ranjeetwaje.wealthmanagement.viewmodelfactory.SignUpViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    val CUSTOM_PREF_NAME = "User_data"

    private val signUpViewModel: SignUpViewModel by lazy {
        ViewModelProvider(this, SignUpViewModelFactory(this.requireActivity()))
            .get(SignUpViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentSignUpBinding>(inflater, R.layout.fragment_sign_up,
            container, false)

        binding.signUpViewModel = signUpViewModel
        binding.lifecycleOwner = this

        val preferences = activity?.applicationContext?.let {
            PreferenceHelper.customPreference(
                it,
                CUSTOM_PREF_NAME
            )
        }

        binding.loginButton.setOnClickListener {
//            it.findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

        signUpViewModel.isCreatedSuccessfully.observe(this.viewLifecycleOwner, Observer {
            if (it) {
//                view?.findNavController()?.navigate(R.id.action_signUpFragment_to_loginFragment)
                Toast.makeText(this.context, "User created successfully", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this.context, "Failed to create a user. Please try again.", Toast.LENGTH_LONG).show()
            }
        })

        signUpViewModel.statusMessage.observe(this.viewLifecycleOwner, Observer {
            it.getContentIfNotHandled().let {
                Toast.makeText(this.context, it, Toast.LENGTH_LONG).show()
            }
        })

        return binding.root
    }

}
