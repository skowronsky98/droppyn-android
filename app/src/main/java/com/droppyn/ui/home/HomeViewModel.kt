package com.droppyn.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.droppyn.database.entity.DatabaseBrand
import com.droppyn.database.getDatabase
import com.droppyn.domain.Brand
import com.droppyn.domain.Media
import com.droppyn.repository.DroppynRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val database = getDatabase(application)
    private val droppynRepository = DroppynRepository(database)

    init {
        refreshData()

    }

    private fun refreshData() {
        viewModelScope.launch {
            droppynRepository.refreshBrands()

//            _text.value = droppynRepository.brands.value?.size.toString()
//            _properties = personalTrainerRepository.advertisments
        }
    }

    fun getBrands(): LiveData<List<Brand>> = droppynRepository.brands


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