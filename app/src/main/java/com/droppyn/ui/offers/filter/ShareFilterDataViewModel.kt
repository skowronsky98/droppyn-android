package com.droppyn.ui.offers.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droppyn.domain.Size

class ShareFilterDataViewModel: ViewModel() {
    private val _item = MutableLiveData<Size>()
    val item: LiveData<Size> = _item

    fun setItem(size: Size){
        _item.value = size
    }

    fun clearData(){
        _item.value = null
    }
}