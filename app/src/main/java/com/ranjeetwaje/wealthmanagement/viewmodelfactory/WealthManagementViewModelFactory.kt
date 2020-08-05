package com.ranjeetwaje.wealthmanagement.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ranjeetwaje.wealthmanagement.repository.WealthManagementRepository
import com.ranjeetwaje.wealthmanagement.viewmodel.WealthManagementViewModel

class WealthManagementViewModelFactory(private val repository: WealthManagementRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WealthManagementViewModel::class.java)) {
            return WealthManagementViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown View Model Class")
    }
}