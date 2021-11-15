package com.droppyn.auth.signup

import android.app.Application
import androidx.lifecycle.*
import com.droppyn.database.getDatabase
import com.droppyn.repository.AuthRepository
import kotlinx.coroutines.launch

class SignupViewModel(app: Application) : AndroidViewModel(app) {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val username = MutableLiveData<String>()

    private val database = getDatabase(app)
    private val authRepo = AuthRepository(database)


    private val _eventNavLogin = MutableLiveData<Boolean>()
    val eventNavLogin : LiveData<Boolean>
        get() = _eventNavLogin

    private val _eventSignup = MutableLiveData<Boolean>()
    val eventSignup:LiveData<Boolean>
        get() = _eventSignup

    private val _eventError = MutableLiveData<Boolean>()
    val eventError :LiveData<Boolean>
        get() = _eventError

    fun eventSignupStart(){
        email.value?.let { e ->
            username.value?.let { u ->
                password.value?.let { p ->
                    viewModelScope.launch {
                        val result = authRepo.register(email = e, username = u, password = p)
                        _eventSignup.value = result
                        if (!result) { eventErrorStart() }
                    }
                }
            }
        }

    }

    fun eventErrorStart(){
        _eventError.value = true
    }

    fun eventErrorFinished(){
        _eventError.value = false
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

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SignupViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}