package com.droppyn.ui.offers

import android.app.Application
import androidx.lifecycle.*
import com.droppyn.database.getDatabase
import com.droppyn.domain.Shoe
import com.droppyn.repository.DroppynRepository
import kotlinx.coroutines.launch

class OffersViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val droppynRepository = DroppynRepository(database)
    var offers = droppynRepository.offers


    private val _shoe = MutableLiveData<Shoe>()
    val shoe: MutableLiveData<Shoe> = _shoe

    fun setFilter(shoe: Shoe){
        _shoe.value = shoe

        //TODO rework filter
        offers = droppynRepository.getFilteredOffers(shoe)
    }


    init {
        refreshData()

    }

    private val _navToOfferDitail = MutableLiveData<Boolean>()
    val navToOfferDitail: LiveData<Boolean>
        get() = _navToOfferDitail

    fun navigateToOfferDetail(){
        _navToOfferDitail.value = true
    }

    fun navigateToOfferDetailFinished(){
        _navToOfferDitail.value = false
    }


    private val _navBackToShop = MutableLiveData<Boolean>()
    val navBackToShop: LiveData<Boolean>
        get() = _navBackToShop

    fun navBackToShop(){
        _navBackToShop.value = true
    }

    fun navBackToShopFinished(){
        _navBackToShop.value = false
    }

    fun refreshData() {
        viewModelScope.launch {
            droppynRepository.refreshOffers()
        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(OffersViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return OffersViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}