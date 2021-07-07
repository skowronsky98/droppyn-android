package com.droppyn.database.entity.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.droppyn.database.entity.DatabaseShoesAndBrand

@Dao
interface ShoesAndBrandDao {
    @Transaction
    @Query("SELECT * FROM shoes")
    fun getShoesAndBrands(): LiveData<List<DatabaseShoesAndBrand>>
}