package com.droppyn.ui.settings

import android.app.Application
import androidx.lifecycle.*
import com.droppyn.database.getDatabase
import com.droppyn.domain.User
import com.droppyn.repository.DroppynRepository
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application)  {
    private val database = getDatabase(application)
    private val droppynRepository = DroppynRepository(database)

    var profile = MutableLiveData<User>()

    val firstname = MutableLiveData<String>()
    val surname = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val phone = MutableLiveData<String>()
    val bio = MutableLiveData<String>()



    init {
        viewModelScope.launch {
            droppynRepository.getProfile()?.let { profile.value = it
                firstname.value = it.firstname
                surname.value = it.surname
                email.value = it.email
                phone.value = it.phone
                bio.value = it.bio
            }
        }
    }

    private val _navBack = MutableLiveData<Boolean>()
    val navBack : LiveData<Boolean>
        get() = _navBack

    fun navBack(){
        _navBack.value = true
    }

    fun navBackFinished(){
        _navBack.value = false
    }


    fun saveSettings(){

    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SettingsViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}