package com.ranjeetwaje.wealthmanagement.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ranjeetwaje.wealthmanagement.database.CategoryOptionEntity
import com.ranjeetwaje.wealthmanagement.database.TransactionTypeEntity
import com.ranjeetwaje.wealthmanagement.database.WealthManagementEntity
import com.ranjeetwaje.wealthmanagement.repository.WealthManagementRepository
import com.ranjeetwaje.wealthmanagement.utils.Event
import kotlinx.coroutines.launch
import java.util.*

class WealthManagementViewModel(private val repository: WealthManagementRepository): ViewModel(),
    Observable {

    val expenseList = repository.expenseList
    val transactionTypes = repository.transactionTypes
    val categoryList = repository.categoryList

    private val _isCreatedSuccessfully = MutableLiveData<Boolean>()
    val isCreatedSuccessfully: LiveData<Boolean>
        get() = _isCreatedSuccessfully

    private val _message = MutableLiveData<Event<String>>()
    val message: MutableLiveData<Event<String>>
        get() = _message

    @Bindable
    val expenseType = MutableLiveData<String>()

    @Bindable
    val expenseRadioChecked = MutableLiveData<Boolean>()

    @Bindable
    val incomeRadioChecked = MutableLiveData<Boolean>()

    @Bindable
    val expensePlace = MutableLiveData<String>()

    @Bindable
    val expenseAmount = MutableLiveData<String>()

    @Bindable
    val category = MutableLiveData<String>()

    @Bindable
    val transactionType = MutableLiveData<String>()

    @Bindable
    val note = MutableLiveData<String>()

    @Bindable
    val transactionDate = MutableLiveData<String>()

    init {
        insertCategories()
        insertTransactionTypes()
    }

    private fun insertTransactionTypes() {
        viewModelScope.launch {
            repository.deleteTransactionTypes()
        }
        val transactionType = arrayOf("Cash", "UPI", "Net Banking", "Credit card", "Debit Card")
        for (i in transactionType.indices) {
            insertTransactionTypes(
                TransactionTypeEntity(0, transactionType[i])
            )
        }
    }

    private fun insertCategories() {
        viewModelScope.launch {
            repository.deleteCategories()
        }
        val categories = arrayOf("Automobiles", "Education")
        for (i in categories.indices) {
            insertCategory(
                CategoryOptionEntity(0, categories[i])
            )
        }
    }


    fun insert() {
        if (expenseType.value.isNullOrEmpty() && expensePlace.value.isNullOrEmpty() && expenseAmount.value.isNullOrEmpty()) {
            _message.value = Event("Please enter Expense Type, Place and amount")
        } else {
            val currentDate = Date().toString()
            insert(
                WealthManagementEntity(0, getExpenseType(), "",
                    expenseAmount.value!!, transactionDate.value!!, category.value!!, transactionType.value!!, note.value!!)
            )

        }
    }

    fun update() {
        if (!expenseType.value.isNullOrEmpty() && !expensePlace.value.isNullOrEmpty() && expenseAmount.value.isNullOrEmpty()) {
            val currentDate = Date().toString()
            update(
                WealthManagementEntity(0, getExpenseType(), "",
                    expenseAmount.value!!, transactionDate.value!!, category.value!!, transactionType.value!!, note.value!!)
            )
        } else {
            _message.value = Event("Please enter Expense Type, Place and amount")
        }
    }

    private fun insert(entity: WealthManagementEntity) = viewModelScope.launch {
        val newId = repository.insert(entity)
        _isCreatedSuccessfully.value = newId > -1
    }

    private fun insertCategory(entity: CategoryOptionEntity) = viewModelScope.launch {
        val newId = repository.insert(entity)
    }

    private fun insertTransactionTypes(entity: TransactionTypeEntity) = viewModelScope.launch {
        val newId = repository.insert(entity)
    }

    private fun update(entity: WealthManagementEntity) = viewModelScope.launch {
        val itemsUpdated = repository.update(entity)
    }

    private fun getExpenseType(): String {
        return when {
            expenseRadioChecked.value!! -> {
                "Expense"
            }
            incomeRadioChecked.value!! -> {
                "Income"
            }
            else -> {
                "Expense"
            }
        }
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}