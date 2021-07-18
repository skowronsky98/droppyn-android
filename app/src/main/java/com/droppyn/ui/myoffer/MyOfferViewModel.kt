package com.droppyn.ui.myoffer

import android.util.Log
import android.widget.EditText
import androidx.databinding.InverseMethod
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droppyn.domain.Offer

class MyOfferViewModel : ViewModel() {
    private val _myOffer = MutableLiveData<Offer>()
    val myOffer: MutableLiveData<Offer> = _myOffer

    var price = MutableLiveData<String>()
    var bio = MutableLiveData<String>()

    fun setMyOffer(offer: Offer){
        _myOffer.value = offer

        price.value = offer.price.toString()
        bio.value = offer.bio
    }

    fun save(){
        _myOffer.value?.price = price.value?.toDouble() ?: 0.0
        _myOffer.value?.bio = bio.value.toString()

        Log.i("binding", _myOffer.value?.price.toString())
        Log.i("binding", _myOffer.value?.bio.toString())

    }

}