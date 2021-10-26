package com.droppyn.ui.offers.filter

import android.app.Application
import androidx.lifecycle.*
import com.droppyn.database.getDatabase
import com.droppyn.domain.Size
import com.droppyn.repository.DroppynRepository

class FilterViewModel(application: Application) : AndroidViewModel(application) {


    private val database = getDatabase(application)
    private val droppynRepository = DroppynRepository(database)

    val sizeChart = droppynRepository.sizechart

    var size = MutableLiveData<Size>()


    private val _navToHome = MutableLiveData<Boolean>()
    val navToHome : LiveData<Boolean>
        get() = _navToHome

    fun navigateToHomeFragment(){
        _navToHome.value = true
    }

    fun navigationToHomeFinished(){
        _navToHome.value = false
    }

    fun setSize(index: Int) {
        sizeChart.value?.get(index).let { size.value = it }

    }

    private val _applyFilter = MutableLiveData<Boolean>()
    val applyFilter : LiveData<Boolean>
        get() = _applyFilter

    fun applyFilter(){
        _applyFilter.value = true
    }

    fun applyFilterFinished(){
        _applyFilter.value = false
    }


    private val _removeFilter = MutableLiveData<Boolean>()
    val removeFilter : LiveData<Boolean>
        get() = _removeFilter

    fun removeFilter(){
        _removeFilter.value = true
    }

    fun removeFilterFinished(){
        _removeFilter.value = false
    }



    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FilterViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FilterViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}