package com.droppyn.ui.myoffer

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.droppyn.database.getDatabase
import com.droppyn.domain.Offer
import com.droppyn.repository.DroppynRepository
import kotlinx.coroutines.launch

class MyOfferViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val droppynRepository = DroppynRepository(database)

    private val _myOffer = MutableLiveData<Offer>()
    val myOffer: MutableLiveData<Offer> = _myOffer

    var price = MutableLiveData<String>()

    fun setMyOffer(offer: Offer){
        _myOffer.value = offer

        price.value = offer.price.toString()
    }

    fun save(){
        _myOffer.value?.price = price.value?.toDouble() ?: 0.0
        saveData()
        //navigateToHomeFragment()
    }


    private val _navToHome = MutableLiveData<Boolean>()
    val navToHome : LiveData<Boolean>
        get() = _navToHome

    fun navigateToHomeFragment(){
        Log.i("fun","dziala")
        _navToHome.value = true
        Log.i("fun","dziala")
    }

    fun navigationToHomeFinished(){
        _navToHome.value = false
    }

    fun essa(){
        Log.i("fun","dziala")
    }

    private fun saveData() {

        viewModelScope.launch {
            _myOffer.value?.let { droppynRepository.addMyOfferToDatabase(it, ::navigateToHomeFragment) }
        }
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