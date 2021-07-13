package com.droppyn.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droppyn.domain.Offer

class MyOfferListViewModel: ViewModel() {
    private val _item = MutableLiveData<Offer>()
    val item: LiveData<Offer> = _item

    fun setItem(offer: Offer){
        _item.value = offer
    }

//    fun itemPassed(){
//        _item.value = null
//    }

}