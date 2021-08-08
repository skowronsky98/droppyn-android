package com.droppyn.ui.shoepicker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.droppyn.database.getDatabase
import com.droppyn.repository.DroppynRepository

class ShoesBottomSheetViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val droppynRepository = DroppynRepository(database)
    val shoes = droppynRepository.shoes



    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ShoesBottomSheetViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ShoesBottomSheetViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}