package com.ranjeetwaje.wealthmanagement.viewmodel

import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ranjeetwaje.wealthmanagement.database.WealthManagementEntity
import com.ranjeetwaje.wealthmanagement.repository.WealthManagementRepository
import com.ranjeetwaje.wealthmanagement.utils.Event
import kotlinx.coroutines.launch
import java.util.*

class WealthManagementViewModel(private val repository: WealthManagementRepository): ViewModel(),
    Observable {

    val expenseList = repository.expenseList

    private val _isCreatedSuccessfully = MutableLiveData<Boolean>()
    val isCreatedSuccessfully: LiveData<Boolean>
        get() = _isCreatedSuccessfully

    private val _message = MutableLiveData<Event<String>>()
    val message: MutableLiveData<Event<String>>
        get() = _message

    @Bindable
    val expenseType = MutableLiveData<String>()

    @Bindable
    val expensePlace = MutableLiveData<String>()

    @Bindable
    val expenseAmount = MutableLiveData<String>()

    fun insert() {
        if (expenseType.value.isNullOrEmpty() && expensePlace.value.isNullOrEmpty() && expenseAmount.value.isNullOrEmpty()) {
            _message.value = Event("Please enter Expense Type, Place and amount")
        } else {
            val currentDate = Date().toString()
            insert(
                WealthManagementEntity(0, expenseType.value!!, expensePlace.value!!,
                    expenseAmount.value!!, currentDate)
            )

        }
    }

    fun update() {
        if (!expenseType.value.isNullOrEmpty() && !expensePlace.value.isNullOrEmpty() && expenseAmount.value.isNullOrEmpty()) {
            val currentDate = Date().toString()
            update(
                WealthManagementEntity(0, expenseType.value!!, expensePlace.value!!,
                    expenseAmount.value!!, currentDate)
            )
        } else {
            _message.value = Event("Please enter Expense Type, Place and amount")
        }
    }

    fun setExpenseType(type: String) {
        expenseType.value = type
    }

    private fun insert(entity: WealthManagementEntity) = viewModelScope.launch {
        val newId = repository.insert(entity)
        _isCreatedSuccessfully.value = newId > -1
    }

    private fun update(entity: WealthManagementEntity) = viewModelScope.launch {
        val itemsUpdated = repository.update(entity)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}