package com.droppyn.auth.login

import android.app.Application
import androidx.lifecycle.*
import com.droppyn.database.getDatabase
import com.droppyn.repository.DroppynRepository

class LoginViewModel(app: Application) : AndroidViewModel(app) {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()


    private val database = getDatabase(app)
    private val repository = DroppynRepository(database)

    private val _eventLogin = MutableLiveData<Boolean>()
    val eventLogin :LiveData<Boolean>
        get() = _eventLogin

    private val _eventNavSignup = MutableLiveData<Boolean>()
    val eventNavSignup : LiveData<Boolean>
        get() = _eventNavSignup

    fun eventLoginStart() {
        _eventLogin.value = true
//        getMentee()
    }

    fun eventLoginFinished(){
        _eventLogin.value = false
    }

    fun eventNavSignupStart(){
        _eventNavSignup.value = true
    }

    fun eventNavSignupFinished(){
        _eventNavSignup.value = false
    }

//    fun getMentee(){
//
//        viewModelScope.launch {
//            email.value?.let { repository.getMentee(it) }
//        }
//
//    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return LoginViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}