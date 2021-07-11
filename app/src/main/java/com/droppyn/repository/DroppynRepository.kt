package com.droppyn.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.droppyn.database.DroppynDatabase
import com.droppyn.database.entity.DatabaseShoe
import com.droppyn.database.entity.asDomainModel
import com.droppyn.database.entity.databaseUserAndSizeToDomain
import com.droppyn.domain.*
import com.droppyn.network.DroppynApi
import com.droppyn.network.dto.NetworkBrandContainer
import com.droppyn.network.dto.NetworkShoeContainer
import com.droppyn.network.dto.NetworkSizeContainer
import com.droppyn.network.dto.asDatabaseModel
import com.droppyn.uitl.TestUtil
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

    val users: LiveData<List<User>> = Transformations.map(database.userAndSizeDao.getUsersAndSize()){
        it.asDomainModel()
    }


    val offers: LiveData<List<Offer>> = Transformations.map(database.offerAndRelationsDao.getOffersAndRelations()){
        it.asDomainModel()
    }

    val myOffers: LiveData<List<Offer>> = Transformations.map(database.offerAndRelationsDao.getMyOffersAndRelations()){
        it.asDomainModel()
    }



    suspend fun getUser() = withContext(Dispatchers.IO){
        database.userAndSizeDao.getUserAndSize()?.let { databaseUserAndSizeToDomain(it) }
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
                val shoes = DroppynApi.retrofitService.getShoesProperties()
                if(shoes.isNotEmpty()) {
                    database.shoeDao.deleteAll()
//                    shoes.forEach { Log.i("retrofit",it.model) }
                }
                database.shoeDao.insertAll(*NetworkShoeContainer(shoes).asDatabaseModel())
            } catch (e: Exception){
                Log.i("retrofit",e.message.toString())
            }
        }
    }

    suspend fun refreshSizeChart(){
        withContext(Dispatchers.IO){
            try {
                val sizechart = DroppynApi.retrofitService.getSizeChartProperties()
                if(sizechart.isNotEmpty()) {
                    database.sizeDao.deleteAll()
//                    sizechart.forEach { Log.i("retrofit",it.model) }
                }
                database.sizeDao.insertAll(*NetworkSizeContainer(sizechart).asDatabaseModel())
            } catch (e: Exception){
                Log.i("retrofit",e.message.toString())
            }
        }
    }

    // TODO delete it's just for testing
    suspend fun addTestDataToDatabase(){
        withContext(Dispatchers.IO){
            database.sizeDao.insert(TestUtil.createDatabaseSize())
            database.shoeDao.insert(TestUtil.createDatabaseShoe())
            database.userDao.insert(TestUtil.createDatabseUser())
            database.offerDao.insert(TestUtil.createDatabaseOffer())
            database.myOfferDao.insert(TestUtil.createDatabaseMyOffer())

        }
    }


}