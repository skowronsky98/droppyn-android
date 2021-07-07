package com.droppyn.database.entity

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoeDao {
    @Query("select * from shoes")
    fun getShoes(): LiveData<List<DatabaseShoe>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg databaseShoe: DatabaseShoe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(databaseBrand: DatabaseShoe)

    @Query("DELETE FROM shoes")
    fun deleteAll()


}