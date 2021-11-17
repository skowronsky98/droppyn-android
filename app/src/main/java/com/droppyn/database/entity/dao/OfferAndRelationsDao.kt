package com.droppyn.database.entity.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.droppyn.database.entity.DatabaseMyOfferAndRelations
import com.droppyn.database.entity.DatabaseOfferAndRelations

@Dao
interface OfferAndRelationsDao {
    @Transaction
    @Query("SELECT * FROM offers ORDER BY price ASC")
    fun getOffersAndRelations(): LiveData<List<DatabaseOfferAndRelations>>

    @Transaction
    @Query("SELECT * FROM offers where idShoe=:idShoe ORDER BY price ASC")
    fun getFilteredOffersAndRelations(idShoe: String): LiveData<List<DatabaseOfferAndRelations>>

    @Transaction
    @Query("SELECT * FROM offers where idShoe=:idShoe AND idSize=:idSize ORDER BY price ASC")
    fun getFilteredBySizeOffersAndRelations(idShoe: String, idSize: String): LiveData<List<DatabaseOfferAndRelations>>

    @Transaction
    @Query("SELECT * FROM myoffers ORDER BY id DESC")
    fun getMyOffersAndRelations(): LiveData<List<DatabaseMyOfferAndRelations>>
}