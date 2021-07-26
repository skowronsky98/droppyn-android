package com.droppyn.ui.offers.detail

import android.app.Application
import androidx.lifecycle.*
import com.droppyn.database.getDatabase
import com.droppyn.domain.Offer
import com.droppyn.repository.DroppynRepository

class OfferDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val droppynRepository = DroppynRepository(database)

    private val _offer = MutableLiveData<Offer>()
    val offer: LiveData<Offer>
        get() = _offer

    fun setOffer(offer: Offer){
        _offer.value = offer
    }


    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(OfferDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return OfferDetailViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}