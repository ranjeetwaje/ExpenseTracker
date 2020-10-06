package com.ranjeetwaje.wealthmanagement.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ranjeetwaje.wealthmanagement.R
import com.ranjeetwaje.wealthmanagement.databinding.ActivityLoginBinding
import com.ranjeetwaje.wealthmanagement.utils.PreferenceHelper
import com.ranjeetwaje.wealthmanagement.utils.PreferenceHelper.isUserLoggedIn
import com.ranjeetwaje.wealthmanagement.viewmodel.SignUpViewModel
import com.ranjeetwaje.wealthmanagement.viewmodelfactory.SignUpViewModelFactory

class LoginActivity : AppCompatActivity() {

    private val CUSTOM_PREF_NAME = "User_data"

    private val signUpViewModel: SignUpViewModel by lazy {
        ViewModelProvider(this, SignUpViewModelFactory(this))
            .get(SignUpViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(
            this,
            R.layout.activity_login
        )

        binding.loginViewModel = signUpViewModel
        binding.lifecycleOwner = this

        val preferences = this.applicationContext?.let {
            PreferenceHelper.customPreference(
                it,
                CUSTOM_PREF_NAME
            )
        }

        if (preferences?.isUserLoggedIn!!) {
//            this.findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
//            Navigation.findNavController(binding.root).navigate(R.id.mainActivity)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.signupButton.setOnClickListener {
//            Navigation.findNavController(binding.root).navigate(R.id.signUpActivity)
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        signUpViewModel.isUserExists.observe(this, Observer { userExists ->
            if (userExists) {
//                this.findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
//                Navigation.findNavController(binding.root).navigate(R.id.mainActivity)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "User is unavailable. Please create user by clicking on SignUp button.", Toast.LENGTH_LONG).show()
            }
        })

        signUpViewModel.statusMessage.observe(this, Observer { it ->
            it.getContentIfNotHandled().let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }
}