package com.droppyn.ui.shoepicker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droppyn.domain.Shoe

class ShareDataShoeViewModel: ViewModel() {
    private val _item = MutableLiveData<Shoe>()
    val item: LiveData<Shoe> = _item

    fun setItem(shoe: Shoe){
        _item.value = shoe
    }
}