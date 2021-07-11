package com.droppyn.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.droppyn.database.entity.DatabaseBrand
import com.droppyn.database.entity.DatabaseShoesAndBrand
import com.droppyn.database.getDatabase
import com.droppyn.domain.*
import com.droppyn.repository.DroppynRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val database = getDatabase(application)
    private val droppynRepository = DroppynRepository(database)

    init {
        refreshData()
    }

    private fun refreshData() {
        viewModelScope.launch {
            droppynRepository.refreshBrands()
            droppynRepository.refreshSheos()
            droppynRepository.refreshSizeChart()


//            droppynRepository.addTestDataToDatabase()

//            droppynRepository.getUser()?.let { Log.i("room", it.firstname) }


//            Log.i("room",getUser().email)
//            _text.value = droppynRepository.brands.value?.size.toString()
//            _properties = personalTrainerRepository.advertisments
        }
    }

    fun getBrands(): LiveData<List<Brand>> = droppynRepository.brands
    fun getShoes(): LiveData<List<Shoe>> = droppynRepository.shoes
    fun getSizeChart(): LiveData<List<Size>> = droppynRepository.sizechart
    fun getUsers(): LiveData<List<User>> = droppynRepository.users



    fun getOffers(): LiveData<List<Offer>> = droppynRepository.offers
    fun getMyOffers(): LiveData<List<Offer>> = droppynRepository.myOffers



    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}