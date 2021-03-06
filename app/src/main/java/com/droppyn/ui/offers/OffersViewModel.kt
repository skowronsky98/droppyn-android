package com.droppyn.ui.offers

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.droppyn.database.getDatabase
import com.droppyn.domain.Offer
import com.droppyn.domain.Shoe
import com.droppyn.domain.Size
import com.droppyn.repository.DroppynRepository
import kotlinx.coroutines.launch

class OffersViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val droppynRepository = DroppynRepository(database)

    var offers: LiveData<List<Offer>> = droppynRepository.offers

    private val _shoe = MutableLiveData<Shoe>()
    val shoe: MutableLiveData<Shoe> = _shoe

    private val _size = MutableLiveData<Size>()
    val size: MutableLiveData<Size> = _size

    fun setFilter(shoe: Shoe){
        _shoe.value = shoe

        //TODO rework filter
        offers = droppynRepository.getFilteredOffers(shoe)
    }

    fun setFilter(size: Size){
        _size.value = size
//        _shoe.value?.let { offers = droppynRepository.getFilteredBySizeOffers(it,size) }


        offers = Transformations.map(offers){
            it.filter {
                it.size.id.contentEquals(size.id)
            }
        }

        offers.value?.let { Log.i("filter", it.size.toString()) }
    }


    fun refreshData() {
        viewModelScope.launch {
            droppynRepository.refreshOffers()
        }
    }

    private val _navToOfferDetail = MutableLiveData<Boolean>()
    val navToOfferDetail: LiveData<Boolean>
        get() = _navToOfferDetail

    fun navigateToOfferDetail(){
        _navToOfferDetail.value = true
    }

    fun navigateToOfferDetailFinished(){
        _navToOfferDetail.value = false
    }

    private val _navToFilter = MutableLiveData<Boolean>()
    val navToFilter: LiveData<Boolean>
        get() = _navToFilter

    fun navigateToFilter(){
        _navToFilter.value = true
    }

    fun navigateToFilterFinished(){
        _navToFilter.value = false
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