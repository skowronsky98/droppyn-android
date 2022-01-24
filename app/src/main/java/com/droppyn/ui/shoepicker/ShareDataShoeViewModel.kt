package com.droppyn.ui.shoepicker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droppyn.domain.Shoe

class ShareDataShoeViewModel: ViewModel() {
    private val _item = MutableLiveData<Shoe>()
    val item: LiveData<Shoe> = _item

    private val _active = MutableLiveData<Boolean>()
    val active: LiveData<Boolean> = _active


    fun setItem(shoe: Shoe){
        _active.value = true
        _item.value = shoe
    }

    fun desacite(){
        _active.value = false
    }

}