package com.droppyn.database.entity.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.droppyn.database.entity.DatabaseOffer

@Dao
interface OfferDao {
    @Query("select * from offers")
    fun getOffers(): LiveData<List<DatabaseOffer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg databaseOffer: DatabaseOffer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(databaseOffer: DatabaseOffer)

    @Query("DELETE FROM offers")
    fun deleteAll()

    @Query("DELETE FROM offers WHERE id = :id")
    fun deleteById(id: String)
}