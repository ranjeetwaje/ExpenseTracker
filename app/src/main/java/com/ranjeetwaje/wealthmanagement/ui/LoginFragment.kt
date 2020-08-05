package com.ranjeetwaje.wealthmanagement.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ranjeetwaje.wealthmanagement.R
import com.ranjeetwaje.wealthmanagement.databinding.FragmentLoginBinding
import com.ranjeetwaje.wealthmanagement.utils.PreferenceHelper.customPreference
import com.ranjeetwaje.wealthmanagement.utils.PreferenceHelper.isUserLoggedIn
import com.ranjeetwaje.wealthmanagement.viewmodel.SignUpViewModel
import com.ranjeetwaje.wealthmanagement.viewmodelfactory.SignUpViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    private val CUSTOM_PREF_NAME = "User_data"

    private lateinit var binding: FragmentLoginBinding

    private val signUpViewModel: SignUpViewModel by lazy {
        ViewModelProvider(this, SignUpViewModelFactory(this.requireActivity()))
            .get(SignUpViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login,
            container, false)

        binding.loginViewModel = signUpViewModel
        binding.lifecycleOwner = this

        val preferences = activity?.applicationContext?.let { customPreference(it, CUSTOM_PREF_NAME) }

        if (preferences?.isUserLoggedIn!!) {
            this.findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }

        binding.signupButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        signUpViewModel.isUserExists.observe(this.viewLifecycleOwner, Observer { userExists ->
            if (userExists) {
                this.view?.findNavController()?.navigate(R.id.action_loginFragment_to_homeFragment)
            } else {
                Toast.makeText(this.context, "User is unavailable. Please create user by clicking on SignUp button.", Toast.LENGTH_LONG).show()
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
