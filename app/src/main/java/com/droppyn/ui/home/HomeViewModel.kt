package com.droppyn.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droppyn.network.DroppynApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun refreshData() {
        viewModelScope.launch {
            refreshBrands()
//            _properties = personalTrainerRepository.advertisments
        }
    }


    suspend fun refreshBrands() {
        withContext(Dispatchers.IO){
            try {
                val brands = DroppynApi.retrofitService.getBrandProperties()
                when {
                    brands.isNotEmpty() -> {
                        Log.i("retrofit",brands.get(0).name)
                    }
                    else -> Log.i("retrofit","empty")
                }


            }catch (e: Exception){
                Log.i("retrofit",e.message.toString())
            }
        }
    }
}