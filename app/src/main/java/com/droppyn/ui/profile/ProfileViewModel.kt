package com.droppyn.ui.profile

import android.app.Application
import androidx.lifecycle.*
import com.droppyn.database.getDatabase
import com.droppyn.domain.User
import com.droppyn.repository.DroppynRepository
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val droppynRepository = DroppynRepository(database)

    var profile = MutableLiveData<User>()

    init {
        viewModelScope.launch {
            droppynRepository.getProfile()?.let { profile.value = it }
        }
    }

    private val _changeProfilePicture = MutableLiveData<Boolean>()
    val changeProfilePicture : LiveData<Boolean>
        get() = _changeProfilePicture

    fun changeProfilePicture(){
        _changeProfilePicture.value = true
    }

    fun changeProfilePictureFinished(){
        _changeProfilePicture.value = false
    }

    private val _navToSettings = MutableLiveData<Boolean>()
    val navToSettings : LiveData<Boolean>
        get() = _navToSettings

    fun navToSettings(){
        _navToSettings.value = true
    }

    fun navToSettingsFinished(){
        _navToSettings.value = false
    }



    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ProfileViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}