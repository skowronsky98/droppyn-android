package com.droppyn.ui.addoffer

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.droppyn.database.getDatabase
import com.droppyn.domain.*
import com.droppyn.repository.DroppynRepository
import kotlinx.coroutines.launch

class AddOfferViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val droppynRepository = DroppynRepository(database)


    val sizeChart = droppynRepository.sizechart
    val shoes = droppynRepository.shoes

//    val getProfile = droppynRepository.getProfile()

    private var _offerCrator =  OfferCreator()

    val shoe = MutableLiveData<Shoe>()
    val price = MutableLiveData<String>()
    val bio = MutableLiveData<String>()


    init {
        viewModelScope.launch {
            droppynRepository.getProfile()?.let { _offerCrator.user = it }
            droppynRepository.refreshSheos()
            droppynRepository.refreshSizeChart()
        }
    }



    fun setShoe(shoe: Shoe){
        this.shoe.value = shoe
        _offerCrator.shoe = shoe
    }

    private val _showShoeBottomSheet = MutableLiveData<Boolean>()
    val showShoeBottomSheet : LiveData<Boolean>
        get() = _showShoeBottomSheet

    fun showShoeBottomSheet(){
        _showShoeBottomSheet.value = true
    }

    fun showShoeBottomSheetFinished(){
        _showShoeBottomSheet.value = false
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

    fun setSize(index: Int){
        sizeChart.value?.get(index)?.let { _offerCrator.size = it }
    }

    fun setPrice(price: Double){
        _offerCrator.price = price
    }

    fun setBio(bio: String){
        _offerCrator.bio = bio
    }

    fun create(){
        viewModelScope.launch {
            _offerCrator.createOffer()?.let {
                droppynRepository.createMyOffer(it, ::navigateToHomeFragment)
            }
        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddOfferViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AddOfferViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}