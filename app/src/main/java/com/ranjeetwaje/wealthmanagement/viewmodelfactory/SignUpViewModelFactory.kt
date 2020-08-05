package com.ranjeetwaje.wealthmanagement.viewmodelfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ranjeetwaje.wealthmanagement.viewmodel.SignUpViewModel

class SignUpViewModelFactory(val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(context) as T
        }

        throw IllegalArgumentException("Unknown View Model Class")
    }

}