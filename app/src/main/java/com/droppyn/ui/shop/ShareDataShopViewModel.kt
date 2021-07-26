package com.droppyn.ui.shop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droppyn.domain.Offer
import com.droppyn.domain.Shoe

class ShareDataShopViewModel: ViewModel() {
    private val _itemShoe = MutableLiveData<Shoe>()
    val itemShoe: LiveData<Shoe> = _itemShoe

    fun setItem(shoe: Shoe){
        _itemShoe.value = shoe
    }

    private val _itemOffer = MutableLiveData<Offer>()
    val itemOffer: LiveData<Offer> = _itemOffer

    fun setItem(offer: Offer){
        _itemOffer.value = offer
    }

//    fun itemPassed(){
//        _item.value = null
//    }

}