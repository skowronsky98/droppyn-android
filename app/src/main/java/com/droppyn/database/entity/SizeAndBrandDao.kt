package com.droppyn.database.entity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface SizeAndBrandDao {
    @Transaction
    @Query("SELECT * FROM sizechart")
    fun getSizeAndBrands(): LiveData<List<DatabaseSizeAndBrand>>

}