package com.droppyn.ui.myoffer

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.droppyn.database.getDatabase
import com.droppyn.domain.Offer
import com.droppyn.domain.Size
import com.droppyn.repository.DroppynRepository
import kotlinx.coroutines.launch

class MyOfferViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val droppynRepository = DroppynRepository(database)

    val sizeChart = droppynRepository.sizechart

    private val _myOffer = MutableLiveData<Offer>()
    val myOffer: MutableLiveData<Offer> = _myOffer

    var price = MutableLiveData<String>()
    var size = MutableLiveData<Size>()

    lateinit var displaySize: Array<String>

//    lateinit var sizeChart =

    var editable = MutableLiveData<Boolean>(false)

    fun setMyOffer(offer: Offer){
        _myOffer.value = offer

        price.value = offer.price.toString()

        size.value = offer.user.defultSize
    }

    fun save(){
        _myOffer.value?.price = price.value?.toDouble() ?: 0.0
        saveData()
        turnOffEditable()
        //navigateToHomeFragment()
    }

    fun delete(){
        deleteMyOffer()
        navigateToHomeFragment()
    }


    private val _navToHome = MutableLiveData<Boolean>()
    val navToHome : LiveData<Boolean>
        get() = _navToHome

    fun navigateToHomeFragment(){
        _navToHome.value = true
    }

    fun navigationToHomeFinished(){
        _navToHome.value = false
    }

    fun editableToggle(){
        editable.value = editable.value != true
    }

    fun turnOnEditable(){
        editable.value = true
    }

    fun turnOffEditable(){
        editable.value = false
    }

    fun essa(){
        Log.i("fun","dziala")
    }

    private fun deleteMyOffer(){
        viewModelScope.launch {
            _myOffer.value?.let { droppynRepository.deleteMyOffer(it) }
        }
    }

    private fun saveData() {
        viewModelScope.launch {
            _myOffer.value?.let { droppynRepository.addMyOfferToDatabase(it, ::navigateToHomeFragment) }
        }
    }

    fun setSize(index: Int){
        sizeChart.value?.get(index).let { size.value = it }
        size.value?.let { _myOffer.value?.size = it }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MyOfferViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MyOfferViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}