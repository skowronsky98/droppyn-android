package com.droppyn.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.droppyn.database.entity.DatabaseBrand
import com.droppyn.database.entity.DatabaseShoesAndBrand
import com.droppyn.database.getDatabase
import com.droppyn.domain.*
import com.droppyn.repository.DroppynRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val droppynRepository = DroppynRepository(database)
    val myOffers = droppynRepository.myOffers


    init {
        refreshData()
    }



    fun refreshData() {
        viewModelScope.launch {
            droppynRepository.refreshMyOffers()
        }
    }

    private val _navigateToMyOffer = MutableLiveData<Boolean>()
    val navigateToMyOffer: LiveData<Boolean>
        get() = _navigateToMyOffer


    fun navigateToMyOffer() {
        _navigateToMyOffer.value = true
    }

    fun onMyOfferNavigated() {
        _navigateToMyOffer.value = false
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}