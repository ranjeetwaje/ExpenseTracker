package com.ranjeetwaje.wealthmanagement.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.ranjeetwaje.wealthmanagement.R
import com.ranjeetwaje.wealthmanagement.databinding.ActivityLoginBinding
import com.ranjeetwaje.wealthmanagement.databinding.ActivitySignUpBinding
import com.ranjeetwaje.wealthmanagement.utils.PreferenceHelper
import com.ranjeetwaje.wealthmanagement.viewmodel.SignUpViewModel
import com.ranjeetwaje.wealthmanagement.viewmodelfactory.SignUpViewModelFactory

class SignUpActivity : AppCompatActivity() {

    val CUSTOM_PREF_NAME = "User_data"

    private val signUpViewModel: SignUpViewModel by lazy {
        ViewModelProvider(this, SignUpViewModelFactory(this))
            .get(SignUpViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivitySignUpBinding>(
            this,
            R.layout.activity_sign_up
        )

        binding.signUpViewModel = signUpViewModel
        binding.lifecycleOwner = this

        val preferences = this.applicationContext?.let {
            PreferenceHelper.customPreference(
                it,
                CUSTOM_PREF_NAME
            )
        }

        binding.loginButton.setOnClickListener {
//            it.findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
            Navigation.findNavController(it).navigate(R.id.loginActivity)

        }

        signUpViewModel.isCreatedSuccessfully.observe(this, Observer {
            if (it) {
//                this.findNavController()?.navigate(R.id.action_signUpFragment_to_loginFragment)
//                Navigation.findNavController(binding.root).navigate(R.id.loginActivity)
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "User created successfully", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Failed to create a user. Please try again.", Toast.LENGTH_LONG).show()
            }
        })

        signUpViewModel.statusMessage.observe(this, Observer {
            it.getContentIfNotHandled().let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }
}