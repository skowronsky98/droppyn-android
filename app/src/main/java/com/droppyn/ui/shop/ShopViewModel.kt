package com.droppyn.ui.shop

import android.app.Application
import androidx.lifecycle.*
import com.droppyn.database.getDatabase
import com.droppyn.repository.DroppynRepository
import com.droppyn.ui.home.HomeViewModel
import kotlinx.coroutines.launch

class ShopViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val droppynRepository = DroppynRepository(database)
    val shoes = droppynRepository.shoes


    init {
        refreshData()
    }




    fun refreshData() {
        viewModelScope.launch {
            droppynRepository.refreshSheos()
        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ShopViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ShopViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}