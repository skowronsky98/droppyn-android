package com.droppyn.ui.myoffer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droppyn.domain.Offer

class MyOfferViewModel : ViewModel() {
    private val _myOffer = MutableLiveData<Offer>()
    val myOffer: LiveData<Offer> = _myOffer

    fun setMyOffer(offer: Offer){
        _myOffer.value = offer
    }
}