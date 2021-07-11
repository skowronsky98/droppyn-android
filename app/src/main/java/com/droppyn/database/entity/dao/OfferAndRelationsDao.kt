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
    @Query("SELECT * FROM offers")
    fun getOffersAndRelations(): LiveData<List<DatabaseOfferAndRelations>>

    @Transaction
    @Query("SELECT * FROM myoffers")
    fun getMyOffersAndRelations(): LiveData<List<DatabaseMyOfferAndRelations>>
}