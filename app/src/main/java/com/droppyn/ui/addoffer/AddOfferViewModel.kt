package com.droppyn.ui.addoffer

import android.app.Application
import androidx.lifecycle.*
import com.droppyn.database.getDatabase
import com.droppyn.domain.Offer
import com.droppyn.domain.Shoe
import com.droppyn.domain.Size
import com.droppyn.repository.DroppynRepository

class AddOfferViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val droppynRepository = DroppynRepository(database)

    val sizeChart = droppynRepository.sizechart
    val shoes = droppynRepository.shoes

    val offer = MutableLiveData<Offer>()
    private val _offer = MutableLiveData<Offer>()

    val size = MutableLiveData<Size>()
    val shoe = MutableLiveData<Shoe>()
    val price = MutableLiveData<String>()

    private val _showShoeBottomSheet = MutableLiveData<Boolean>()
    val showShoeBottomSheet : LiveData<Boolean>
        get() = _showShoeBottomSheet

    fun showShoeBottomSheet(){
        _showShoeBottomSheet.value = true
    }

    fun closeShoeBottomSheet(){
        _showShoeBottomSheet.value = false
    }

    private val _navToHome = MutableLiveData<Boolean>()
    val navToHome : LiveData<Boolean>
        get() = _navToHome

    fun navigateToHomeFragment(){
        _navToHome.value = true
    }

    fun navigationToHomeFinished(){
        _navToHome.value = false
    }

    fun setSize(index: Int){
        sizeChart.value?.get(index).let { size.value = it }
        size.value?.let { _offer.value?.size = it }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddOfferViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AddOfferViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}