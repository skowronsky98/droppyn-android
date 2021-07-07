package com.droppyn.database.entity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ShoesAndBrandDao {
    @Transaction
    @Query("SELECT * FROM shoes")
    fun getShoesAndBrands(): LiveData<List<DatabaseShoesAndBrand>>
}