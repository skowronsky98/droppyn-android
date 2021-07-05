package com.droppyn.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.droppyn.database.DroppynDatabase
import com.droppyn.database.entity.asDomainModel
import com.droppyn.domain.Brand
import com.droppyn.network.DroppynApi
import com.droppyn.network.dto.NetworkBrandContainer
import com.droppyn.network.dto.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class DroppynRepository(private val database: DroppynDatabase) {
    val brands: LiveData<List<Brand>> =
        Transformations.map(database.brandDao.getBrands()){
            it.asDomainModel()
        }

    suspend fun refreshBrands() {
        withContext(Dispatchers.IO){
            try {
                val brands = DroppynApi.retrofitService.getBrandProperties()
                if(brands.isNotEmpty())
                    database.brandDao.deleteAll()
                database.brandDao.insertAll(*NetworkBrandContainer(brands).asDatabaseModel())
            }catch (e: Exception){
                Log.i("retrofit",e.message.toString())
            }
        }
    }

}