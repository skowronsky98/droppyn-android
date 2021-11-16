package com.droppyn.database.entity.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.droppyn.database.entity.DatabaseMyOffer

@Dao
interface
MyOfferDao {
    @Query("select * from myoffers")
    fun getMyOffers(): LiveData<List<DatabaseMyOffer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg databaseMyOffer: DatabaseMyOffer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(databaseMyOffer: DatabaseMyOffer)

    @Query("DELETE FROM myoffers")
    fun deleteAll()

    @Query("DELETE FROM myoffers WHERE id = :id")
    fun deleteById(id: String)
}