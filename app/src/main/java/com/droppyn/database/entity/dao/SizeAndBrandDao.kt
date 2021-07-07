package com.droppyn.database.entity.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.droppyn.database.entity.DatabaseSizeAndBrand

@Dao
interface SizeAndBrandDao {
    @Transaction
    @Query("SELECT * FROM sizechart")
    fun getSizeAndBrands(): LiveData<List<DatabaseSizeAndBrand>>

}