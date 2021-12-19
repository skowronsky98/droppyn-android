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

    init {
        viewModelScope.launch {
            droppynRepository.getProfile()?.let { profile.value = it }
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