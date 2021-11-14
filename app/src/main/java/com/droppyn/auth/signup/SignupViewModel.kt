package com.droppyn.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignupViewModel : ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val username = MutableLiveData<String>()

    private val _eventNavLogin = MutableLiveData<Boolean>()
    val eventNavLogin : LiveData<Boolean>
        get() = _eventNavLogin

    private val _eventSignup = MutableLiveData<Boolean>()
    val eventSignup:LiveData<Boolean>
        get() = _eventSignup

    fun eventSignupStart(){
        _eventSignup.value = true
    }

    fun eventSignupFinished(){
        _eventSignup.value = false
    }

    fun eventNavLoginStart(){
        _eventNavLogin.value = true
    }

    fun eventNavLoginFinished(){
        _eventNavLogin.value = false
    }
}