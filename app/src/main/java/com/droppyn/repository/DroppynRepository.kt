package com.droppyn.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.droppyn.database.DroppynDatabase
import com.droppyn.database.entity.DatabaseShoe
import com.droppyn.database.entity.DatabaseShoesAndBrand
import com.droppyn.database.entity.asDomainModel
import com.droppyn.domain.Brand
import com.droppyn.domain.Media
import com.droppyn.domain.Shoe
import com.droppyn.domain.Size
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

    val shoes: LiveData<List<Shoe>> =
        Transformations.map(database.shoesAndBrandDao.getShoesAndBrands()){
            it.asDomainModel()
        }

    val sizechart: LiveData<List<Size>> = Transformations.map(database.sizeAndBrandDao.getSizeAndBrands()){
            it.asDomainModel()
        }

    suspend fun refreshBrands() {
        withContext(Dispatchers.IO){
            try {
                val brands = DroppynApi.retrofitService.getBrandProperties()
                if(brands.isNotEmpty()) {
                    database.brandDao.deleteAll()
//                    brands.forEach { Log.i("retrofit",it.name) }
                }
                database.brandDao.insertAll(*NetworkBrandContainer(brands).asDatabaseModel())
            } catch (e: Exception){
                Log.i("retrofit",e.message.toString())
            }
        }
    }

    suspend fun refreshSheos(){
        withContext(Dispatchers.IO){
            try {

            } catch (e: Exception){
                Log.i("retrofit",e.message.toString())
            }
        }
    }

    suspend fun addShoe(){
        withContext(Dispatchers.IO){
            val shoe = DatabaseShoe(id = "1",
                    model = "Yeezy",
                    brandId = "6054beb61f943b17604b19a2",
                    media = Media(imageUrl = "a",smallImageUrl = "b",thumbUrl = "c"))
            database.shoeDao.insert(shoe)


        }
    }

}