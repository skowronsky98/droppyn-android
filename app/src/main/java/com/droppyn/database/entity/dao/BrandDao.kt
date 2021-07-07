package com.droppyn.database.entity.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.droppyn.database.entity.DatabaseBrand

@Dao
interface BrandDao {
    @Query("select * from brands")
    fun getBrands(): LiveData<List<DatabaseBrand>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg databaseBrand: DatabaseBrand)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(databaseBrand: DatabaseBrand)

    @Query("DELETE FROM brands")
    fun deleteAll()
}