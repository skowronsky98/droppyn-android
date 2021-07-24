package com.droppyn.uitl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShareDataViewModel<T>: ViewModel() {
    private val _item = MutableLiveData<T>()
    val item: LiveData<T> = _item

    fun setItem(offer: T){
        _item.value = offer
    }

//    fun itemPassed(){
//        _item.value = null
//    }

}