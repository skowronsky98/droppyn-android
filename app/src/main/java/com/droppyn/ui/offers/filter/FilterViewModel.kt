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

    fun setSize(index: Int): Size {
        sizeChart.value?.get(index).let { size.value = it }

        return size.value!!
        //TODO set size in viewmodel to pass it into offers fragment
//        size.value?.let { _myOffer.value?.size = it }
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