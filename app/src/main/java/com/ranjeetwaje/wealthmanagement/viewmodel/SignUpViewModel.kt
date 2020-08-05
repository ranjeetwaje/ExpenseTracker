package com.ranjeetwaje.wealthmanagement.viewmodel

import android.content.Context
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ranjeetwaje.wealthmanagement.utils.Event
import com.ranjeetwaje.wealthmanagement.utils.PreferenceHelper
import com.ranjeetwaje.wealthmanagement.utils.PreferenceHelper.isUserLoggedIn
import com.ranjeetwaje.wealthmanagement.utils.PreferenceHelper.password
import com.ranjeetwaje.wealthmanagement.utils.PreferenceHelper.userId

class SignUpViewModel(val context: Context) : ViewModel(), Observable {

    private var _userName = MutableLiveData<String>()
    val userName: LiveData<String>
        get() = _userName

    private val _isUserExists = MutableLiveData<Boolean>()
    val isUserExists: LiveData<Boolean>
        get() = _isUserExists

    private val _isUserLoggedIn = MutableLiveData<Boolean>()
    val isUserLoggedIn: LiveData<Boolean>
        get() = _isUserLoggedIn

    private val _isCreatedSuccessfully = MutableLiveData<Boolean>()
    val isCreatedSuccessfully: LiveData<Boolean>
        get() = _isCreatedSuccessfully

    private val _statusMessage = MutableLiveData<Event<String>>()
    val statusMessage: LiveData<Event<String>>
        get() = _statusMessage

    val CUSTOM_PREF_NAME = "User_data"

    @Bindable
    val inputUserName = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()

    @Bindable
    val inputConfirmPassword = MutableLiveData<String>()

    private val pref = context?.applicationContext?.let {
        PreferenceHelper.customPreference(
            it,
            CUSTOM_PREF_NAME
        )
    }

    init {
        _isUserLoggedIn.value = false
    }

    fun addUser() {
        if (inputPassword.value!!.toString() != inputConfirmPassword.value!!.toString()) {
            _statusMessage.value = Event("Please confirm your password")
            inputPassword.value = null
            inputConfirmPassword.value = null
            return
        }
        if (inputPassword.value!!.isNotEmpty() && inputConfirmPassword.value!!.isNotEmpty()) {
//            createNewUser(LoginEntity(inputUserName.value!!, inputPassword.value!!))

            pref?.password = inputPassword.value
            pref?.userId = inputUserName.value
            if (pref?.userId!!.isNotEmpty()) {
                _isCreatedSuccessfully.value = true
            }
        }
        inputUserName.value = null
        inputPassword.value = null
        inputConfirmPassword.value = null
    }

    private fun updatePassword() {

    }

    fun loginUser() {
        if (pref?.userId!!.isNotEmpty()) {
            _isUserExists.value = (inputUserName.value == pref?.userId && inputPassword.value == pref?.password)
            if (_isUserExists.value!!) {
                pref.isUserLoggedIn = true
                Log.e("isUserLoggedIn-signup", "${pref?.isUserLoggedIn!!}")
            }
        } else {
            _statusMessage.value = Event("User doesn't exists. Please create a new user.")
        }
    }


    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}